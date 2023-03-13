package cn.zealon.readingcloud.book.service.impl;

import cn.zealon.readingcloud.common.pojo.xzwresources.MArticles;
import cn.zealon.readingcloud.book.dao.MArticlesDao;
import cn.zealon.readingcloud.book.service.MArticlesService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * 杂志文章表(MArticles)表服务实现类
 *
 * @author makejava
 * @since 2023-03-01 11:13:59
 */
@Service("mArticlesService")
public class MArticlesServiceImpl implements MArticlesService {
    @Resource
    private MArticlesDao mArticlesDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public MArticles queryById(Long id) {
        return this.mArticlesDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param mArticles   筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<MArticles> queryByPage(MArticles mArticles, PageRequest pageRequest) {
        long total = this.mArticlesDao.count(mArticles);
        return new PageImpl<>(this.mArticlesDao.queryAllByLimit(mArticles, pageRequest), pageRequest, total);
    }

    @Override
    public List<MArticles>queryAll(MArticles mArticles){
        return this.mArticlesDao.queryAll(mArticles);
    }
    /**
     * 新增数据
     *
     * @param mArticles 实例对象
     * @return 实例对象
     */
    @Override
    public MArticles insert(MArticles mArticles) {
        this.mArticlesDao.insert(mArticles);
        return mArticles;
    }

    /**
     * 修改数据
     *
     * @param mArticles 实例对象
     * @return 实例对象
     */
    @Override
    public MArticles update(MArticles mArticles) {
        this.mArticlesDao.update(mArticles);
        return this.queryById(mArticles.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.mArticlesDao.deleteById(id) > 0;
    }
}
