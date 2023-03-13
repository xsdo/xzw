package cn.zealon.readingcloud.homepage.service.impl;

import cn.zealon.readingcloud.common.pojo.xzwtasks.AuthTasklog;
import cn.zealon.readingcloud.homepage.dao.AuthTasklogDao;
import cn.zealon.readingcloud.homepage.service.AuthTasklogService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * 认证用户记录表(AuthTasklog)表服务实现类
 *
 * @author makejava
 * @since 2023-03-01 11:11:43
 */
@Service("authTasklogService")
public class AuthTasklogServiceImpl implements AuthTasklogService {
    @Resource
    private AuthTasklogDao authTasklogDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public AuthTasklog queryById(Long id) {
        return this.authTasklogDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param authTasklog 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<AuthTasklog> queryByPage(AuthTasklog authTasklog, PageRequest pageRequest) {
        long total = this.authTasklogDao.count(authTasklog);
        return new PageImpl<>(this.authTasklogDao.queryAllByLimit(authTasklog, pageRequest), pageRequest, total);
    }

    @Override
    public List<AuthTasklog>queryAll(AuthTasklog authTasklog){
        return this.authTasklogDao.queryAll(authTasklog);
    }
    /**
     * 新增数据
     *
     * @param authTasklog 实例对象
     * @return 实例对象
     */
    @Override
    public AuthTasklog insert(AuthTasklog authTasklog) {
        this.authTasklogDao.insert(authTasklog);
        return authTasklog;
    }

    /**
     * 修改数据
     *
     * @param authTasklog 实例对象
     * @return 实例对象
     */
    @Override
    public AuthTasklog update(AuthTasklog authTasklog) {
        this.authTasklogDao.update(authTasklog);
        return this.queryById(authTasklog.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.authTasklogDao.deleteById(id) > 0;
    }
}
