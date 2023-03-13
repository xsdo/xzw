package cn.zealon.readingcloud.account.service.impl;

import cn.zealon.readingcloud.common.pojo.xzwusers.USigndetail;
import cn.zealon.readingcloud.account.dao.USigndetailDao;
import cn.zealon.readingcloud.account.service.USigndetailService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * 签到明细表(USigndetail)表服务实现类
 *
 * @author makejava
 * @since 2023-03-07 09:20:54
 */
@Service("uSigndetailService")
public class USigndetailServiceImpl implements USigndetailService {
    @Resource
    private USigndetailDao uSigndetailDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public USigndetail queryById(Long id) {
        return this.uSigndetailDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param uSigndetail 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<USigndetail> queryByPage(USigndetail uSigndetail, PageRequest pageRequest) {
        long total = this.uSigndetailDao.count(uSigndetail);
        return new PageImpl<>(this.uSigndetailDao.queryAllByLimit(uSigndetail, pageRequest), pageRequest, total);
    }

    @Override
    public List<USigndetail> queryAllByUserId(Long userId){
        USigndetail uSigndetail=new USigndetail();
        uSigndetail.setUserId(userId);
        uSigndetail.setIsused(0);
        return this.uSigndetailDao.queryAll(uSigndetail);
    }

    /**
     * 新增数据
     *
     * @param uSigndetail 实例对象
     * @return 实例对象
     */
    @Override
    public USigndetail insert(USigndetail uSigndetail) {
        this.uSigndetailDao.insert(uSigndetail);
        return uSigndetail;
    }

    /**
     * 修改数据
     *
     * @param uSigndetail 实例对象
     * @return 实例对象
     */
    @Override
    public USigndetail update(USigndetail uSigndetail) {
        this.uSigndetailDao.update(uSigndetail);
        return this.queryById(uSigndetail.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.uSigndetailDao.deleteById(id) > 0;
    }
}
