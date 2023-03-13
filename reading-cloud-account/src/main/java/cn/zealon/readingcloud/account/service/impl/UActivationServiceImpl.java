package cn.zealon.readingcloud.account.service.impl;

import cn.zealon.readingcloud.common.pojo.xzwusers.UActivation;
import cn.zealon.readingcloud.account.dao.UActivationDao;
import cn.zealon.readingcloud.account.service.UActivationService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * 激活码表(UActivation)表服务实现类
 *
 * @author makejava
 * @since 2023-03-01 10:43:52
 */
@Service("uActivationService")
public class UActivationServiceImpl implements UActivationService {
    @Resource
    private UActivationDao uActivationDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public UActivation queryById(Long id) {
        return this.uActivationDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param uActivation 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<UActivation> queryByPage(UActivation uActivation, PageRequest pageRequest) {
        long total = this.uActivationDao.count(uActivation);
        return new PageImpl<>(this.uActivationDao.queryAllByLimit(uActivation, pageRequest), pageRequest, total);
    }

    @Override
    public List<UActivation>queryAll(UActivation uActivation){
        return this.uActivationDao.queryAll(uActivation);
    }

    /**
     * 新增数据
     *
     * @param uActivation 实例对象
     * @return 实例对象
     */
    @Override
    public UActivation insert(UActivation uActivation) {
        this.uActivationDao.insert(uActivation);
        return uActivation;
    }

    /**
     * 修改数据
     *
     * @param uActivation 实例对象
     * @return 实例对象
     */
    @Override
    public UActivation update(UActivation uActivation) {
        this.uActivationDao.update(uActivation);
        return this.queryById(uActivation.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.uActivationDao.deleteById(id) > 0;
    }
}
