package cn.zealon.readingcloud.account.service.impl;

import cn.zealon.readingcloud.common.pojo.xzwusers.UFans;
import cn.zealon.readingcloud.account.dao.UFansDao;
import cn.zealon.readingcloud.account.service.UFansService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * 用户粉丝表(UFans)表服务实现类
 *
 * @author makejava
 * @since 2023-03-01 10:43:54
 */
@Service("uFansService")
public class UFansServiceImpl implements UFansService {
    @Resource
    private UFansDao uFansDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public UFans queryById(Long id) {
        return this.uFansDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param uFans       筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<UFans> queryByPage(UFans uFans, PageRequest pageRequest) {
        long total = this.uFansDao.count(uFans);
        return new PageImpl<>(this.uFansDao.queryAllByLimit(uFans, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param uFans 实例对象
     * @return 实例对象
     */
    @Override
    public UFans insert(UFans uFans) {
        this.uFansDao.insert(uFans);
        return uFans;
    }

    /**
     * 修改数据
     *
     * @param uFans 实例对象
     * @return 实例对象
     */
    @Override
    public UFans update(UFans uFans) {
        this.uFansDao.update(uFans);
        return this.queryById(uFans.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.uFansDao.deleteById(id) > 0;
    }
}
