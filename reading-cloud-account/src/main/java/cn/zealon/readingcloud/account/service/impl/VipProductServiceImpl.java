package cn.zealon.readingcloud.account.service.impl;

import cn.zealon.readingcloud.common.pojo.xzwusers.VipProduct;
import cn.zealon.readingcloud.account.dao.VipProductDao;
import cn.zealon.readingcloud.account.service.VipProductService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * 会员商品表(VipProduct)表服务实现类
 *
 * @author makejava
 * @since 2023-04-26 15:58:16
 */
@Service("vipProductService")
public class VipProductServiceImpl implements VipProductService {
    @Resource
    private VipProductDao vipProductDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public VipProduct queryById(Long id) {
        return this.vipProductDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param vipProduct  筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<VipProduct> queryByPage(VipProduct vipProduct, PageRequest pageRequest) {
        long total = this.vipProductDao.count(vipProduct);
        return new PageImpl<>(this.vipProductDao.queryAllByLimit(vipProduct, pageRequest), pageRequest, total);
    }

    @Override
    public List<VipProduct>queryAll(VipProduct vipProduct){
        return this.vipProductDao.queryAll(vipProduct);
    }

    /**
     * 新增数据
     *
     * @param vipProduct 实例对象
     * @return 实例对象
     */
    @Override
    public VipProduct insert(VipProduct vipProduct) {
        this.vipProductDao.insert(vipProduct);
        return vipProduct;
    }

    /**
     * 修改数据
     *
     * @param vipProduct 实例对象
     * @return 实例对象
     */
    @Override
    public VipProduct update(VipProduct vipProduct) {
        this.vipProductDao.update(vipProduct);
        return this.queryById(vipProduct.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.vipProductDao.deleteById(id) > 0;
    }
}
