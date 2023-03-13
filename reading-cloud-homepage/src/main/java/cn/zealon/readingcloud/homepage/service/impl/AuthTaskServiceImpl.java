package cn.zealon.readingcloud.homepage.service.impl;

import cn.zealon.readingcloud.common.pojo.xzwtasks.AuthTask;
import cn.zealon.readingcloud.homepage.dao.AuthTaskDao;
import cn.zealon.readingcloud.homepage.service.AuthTaskService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * 认证任务表(AuthTask)表服务实现类
 *
 * @author makejava
 * @since 2023-03-01 11:11:42
 */
@Service("authTaskService")
public class AuthTaskServiceImpl implements AuthTaskService {
    @Resource
    private AuthTaskDao authTaskDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public AuthTask queryById(Long id) {
        return this.authTaskDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param authTask    筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<AuthTask> queryByPage(AuthTask authTask, PageRequest pageRequest) {
        long total = this.authTaskDao.count(authTask);
        return new PageImpl<>(this.authTaskDao.queryAllByLimit(authTask, pageRequest), pageRequest, total);
    }

    @Override
    public List<AuthTask>queryAll(AuthTask authTask){
        return this.authTaskDao.queryAll(authTask);
    }

    /**
     * 新增数据
     *
     * @param authTask 实例对象
     * @return 实例对象
     */
    @Override
    public AuthTask insert(AuthTask authTask) {
        this.authTaskDao.insert(authTask);
        return authTask;
    }

    /**
     * 修改数据
     *
     * @param authTask 实例对象
     * @return 实例对象
     */
    @Override
    public AuthTask update(AuthTask authTask) {
        this.authTaskDao.update(authTask);
        return this.queryById(authTask.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.authTaskDao.deleteById(id) > 0;
    }
}
