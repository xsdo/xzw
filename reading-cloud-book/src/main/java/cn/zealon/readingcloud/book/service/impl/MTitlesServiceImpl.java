package cn.zealon.readingcloud.book.service.impl;

import cn.zealon.readingcloud.common.pojo.xzwresources.MTitles;
import cn.zealon.readingcloud.book.dao.MTitlesDao;
import cn.zealon.readingcloud.book.service.MTitlesService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * 杂志目录表(MTitles)表服务实现类
 *
 * @author makejava
 * @since 2023-03-01 11:13:54
 */
@Service("mTitlesService")
public class MTitlesServiceImpl implements MTitlesService {
    @Resource
    private MTitlesDao mTitlesDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public MTitles queryById(Long id) {
        return this.mTitlesDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param mTitles     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<MTitles> queryByPage(MTitles mTitles, PageRequest pageRequest) {
        long total = this.mTitlesDao.count(mTitles);
        return new PageImpl<>(this.mTitlesDao.queryAllByLimit(mTitles, pageRequest), pageRequest, total);
    }

    @Override
    public List<MTitles>queryAll(MTitles mTitles){
        return this.mTitlesDao.queryAll(mTitles);
    }

    /**
     * 新增数据
     *
     * @param mTitles 实例对象
     * @return 实例对象
     */
    @Override
    public MTitles insert(MTitles mTitles) {
        this.mTitlesDao.insert(mTitles);
        return mTitles;
    }

    /**
     * 修改数据
     *
     * @param mTitles 实例对象
     * @return 实例对象
     */
    @Override
    public MTitles update(MTitles mTitles) {
        this.mTitlesDao.update(mTitles);
        return this.queryById(mTitles.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.mTitlesDao.deleteById(id) > 0;
    }
}
