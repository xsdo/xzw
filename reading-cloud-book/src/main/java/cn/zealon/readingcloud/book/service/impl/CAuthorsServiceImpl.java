package cn.zealon.readingcloud.book.service.impl;

import cn.zealon.readingcloud.common.pojo.xzwresources.CAuthors;
import cn.zealon.readingcloud.book.dao.CAuthorsDao;
import cn.zealon.readingcloud.book.service.CAuthorsService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * 作者表(CAuthors)表服务实现类
 *
 * @author makejava
 * @since 2023-03-01 11:14:02
 */
@Service("cAuthorsService")
public class CAuthorsServiceImpl implements CAuthorsService {
    @Resource
    private CAuthorsDao cAuthorsDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public CAuthors queryById(Long id) {
        return this.cAuthorsDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param cAuthors    筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<CAuthors> queryByPage(CAuthors cAuthors, PageRequest pageRequest) {
        long total = this.cAuthorsDao.count(cAuthors);
        return new PageImpl<>(this.cAuthorsDao.queryAllByLimit(cAuthors, pageRequest), pageRequest, total);
    }

    @Override
    public List<CAuthors>queryAll(CAuthors cAuthors){
        return this.cAuthorsDao.queryAll(cAuthors);
    }
    /**
     * 新增数据
     *
     * @param cAuthors 实例对象
     * @return 实例对象
     */
    @Override
    public CAuthors insert(CAuthors cAuthors) {
        this.cAuthorsDao.insert(cAuthors);
        return cAuthors;
    }

    /**
     * 修改数据
     *
     * @param cAuthors 实例对象
     * @return 实例对象
     */
    @Override
    public CAuthors update(CAuthors cAuthors) {
        this.cAuthorsDao.update(cAuthors);
        return this.queryById(cAuthors.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.cAuthorsDao.deleteById(id) > 0;
    }
}
