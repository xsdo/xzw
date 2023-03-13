package cn.zealon.readingcloud.homepage.service.impl;

import cn.zealon.readingcloud.common.pojo.xzwtasks.SuggestUser;
import cn.zealon.readingcloud.homepage.dao.SuggestUserDao;
import cn.zealon.readingcloud.homepage.service.SuggestUserService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * 推荐用户表(SuggestUser)表服务实现类
 *
 * @author makejava
 * @since 2023-03-01 11:11:40
 */
@Service("suggestUserService")
public class SuggestUserServiceImpl implements SuggestUserService {
    @Resource
    private SuggestUserDao suggestUserDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SuggestUser queryById(Long id) {
        return this.suggestUserDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param suggestUser 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<SuggestUser> queryByPage(SuggestUser suggestUser, PageRequest pageRequest) {
        long total = this.suggestUserDao.count(suggestUser);
        return new PageImpl<>(this.suggestUserDao.queryAllByLimit(suggestUser, pageRequest), pageRequest, total);
    }
    @Override
    public List<SuggestUser>queryAll(SuggestUser suggestUser){
        return this.suggestUserDao.queryAll(suggestUser);
    }


    /**
     * 新增数据
     *
     * @param suggestUser 实例对象
     * @return 实例对象
     */
    @Override
    public SuggestUser insert(SuggestUser suggestUser) {
        this.suggestUserDao.insert(suggestUser);
        return suggestUser;
    }

    /**
     * 修改数据
     *
     * @param suggestUser 实例对象
     * @return 实例对象
     */
    @Override
    public SuggestUser update(SuggestUser suggestUser) {
        this.suggestUserDao.update(suggestUser);
        return this.queryById(suggestUser.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.suggestUserDao.deleteById(id) > 0;
    }
}
