package cn.zealon.readingcloud.account.service.impl;

import cn.zealon.readingcloud.common.pojo.xzwusers.UBinding;
import cn.zealon.readingcloud.account.dao.UBindingDao;
import cn.zealon.readingcloud.account.service.UBindingService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * 绑定班级表(UBinding)表服务实现类
 *
 * @author makejava
 * @since 2023-03-01 10:43:53
 */
@Service("uBindingService")
public class UBindingServiceImpl implements UBindingService {
    @Resource
    private UBindingDao uBindingDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public UBinding queryById(Long id) {
        return this.uBindingDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param uBinding    筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<UBinding> queryByPage(UBinding uBinding, PageRequest pageRequest) {
        long total = this.uBindingDao.count(uBinding);
        return new PageImpl<>(this.uBindingDao.queryAllByLimit(uBinding, pageRequest), pageRequest, total);
    }

    @Override
    public List<UBinding>queryAll(UBinding uBinding){
        return this.uBindingDao.queryAll(uBinding);
    }

    @Override
    public List<UBinding> queryByTeacherId(Long teacherId){
        UBinding uBinding=new UBinding();
        uBinding.setIsused(0);
        uBinding.setTeacherId(teacherId);
        return this.uBindingDao.queryAll(uBinding);
    }


    /**
     * 新增数据
     *
     * @param uBinding 实例对象
     * @return 实例对象
     */
    @Override
    public UBinding insert(UBinding uBinding) {
        this.uBindingDao.insert(uBinding);
        return uBinding;
    }

    /**
     * 修改数据
     *
     * @param uBinding 实例对象
     * @return 实例对象
     */
    @Override
    public UBinding update(UBinding uBinding) {
        this.uBindingDao.update(uBinding);
        return this.queryById(uBinding.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.uBindingDao.deleteById(id) > 0;
    }
}
