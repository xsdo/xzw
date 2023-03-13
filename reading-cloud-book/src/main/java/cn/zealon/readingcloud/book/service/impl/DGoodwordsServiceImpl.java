package cn.zealon.readingcloud.book.service.impl;

import cn.zealon.readingcloud.common.pojo.xzwresources.DGoodwords;
import cn.zealon.readingcloud.book.dao.DGoodwordsDao;
import cn.zealon.readingcloud.book.service.DGoodwordsService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * 部编好词表(DGoodwords)表服务实现类
 *
 * @author makejava
 * @since 2023-03-01 11:13:59
 */
@Service("dGoodwordsService")
public class DGoodwordsServiceImpl implements DGoodwordsService {
    @Resource
    private DGoodwordsDao dGoodwordsDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public DGoodwords queryById(Long id) {
        return this.dGoodwordsDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param dGoodwords  筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<DGoodwords> queryByPage(DGoodwords dGoodwords, PageRequest pageRequest) {
        long total = this.dGoodwordsDao.count(dGoodwords);
        return new PageImpl<>(this.dGoodwordsDao.queryAllByLimit(dGoodwords, pageRequest), pageRequest, total);
    }

    @Override
    public List<DGoodwords>queryAll(DGoodwords dGoodwords){
        return this.dGoodwordsDao.queryAll(dGoodwords);
    }
    /**
     * 新增数据
     *
     * @param dGoodwords 实例对象
     * @return 实例对象
     */
    @Override
    public DGoodwords insert(DGoodwords dGoodwords) {
        this.dGoodwordsDao.insert(dGoodwords);
        return dGoodwords;
    }

    /**
     * 修改数据
     *
     * @param dGoodwords 实例对象
     * @return 实例对象
     */
    @Override
    public DGoodwords update(DGoodwords dGoodwords) {
        this.dGoodwordsDao.update(dGoodwords);
        return this.queryById(dGoodwords.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.dGoodwordsDao.deleteById(id) > 0;
    }
}
