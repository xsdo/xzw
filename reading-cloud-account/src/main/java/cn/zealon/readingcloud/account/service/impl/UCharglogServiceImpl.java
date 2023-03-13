package cn.zealon.readingcloud.account.service.impl;

import cn.zealon.readingcloud.common.pojo.xzwusers.UCharglog;
import cn.zealon.readingcloud.account.dao.UCharglogDao;
import cn.zealon.readingcloud.account.service.UCharglogService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * 充值记录表(UCharglog)表服务实现类
 *
 * @author makejava
 * @since 2023-03-10 17:54:34
 */
@Service("uCharglogService")
public class UCharglogServiceImpl implements UCharglogService {
    @Resource
    private UCharglogDao uCharglogDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public UCharglog queryById(Long id) {
        return this.uCharglogDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param uCharglog   筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<UCharglog> queryByPage(UCharglog uCharglog, PageRequest pageRequest) {
        long total = this.uCharglogDao.count(uCharglog);
        return new PageImpl<>(this.uCharglogDao.queryAllByLimit(uCharglog, pageRequest), pageRequest, total);
    }

    @Override
    public List<UCharglog>queryAll(UCharglog uCharglog){
        return this.uCharglogDao.queryAll(uCharglog);
    }

    @Override
    public List<UCharglog>queryByUserId(Long userId){
        UCharglog uCharglog =new UCharglog();
        uCharglog.setIsused(0);
        uCharglog.setUserId(userId);
        return this.uCharglogDao.queryAll(uCharglog);
    }
    /**
     * 新增数据
     *
     * @param uCharglog 实例对象
     * @return 实例对象
     */
    @Override
    public UCharglog insert(UCharglog uCharglog) {
        this.uCharglogDao.insert(uCharglog);
        return uCharglog;
    }

    /**
     * 修改数据
     *
     * @param uCharglog 实例对象
     * @return 实例对象
     */
    @Override
    public UCharglog update(UCharglog uCharglog) {
        this.uCharglogDao.update(uCharglog);
        return this.queryById(uCharglog.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.uCharglogDao.deleteById(id) > 0;
    }
}
