package cn.zealon.readingcloud.book.service.impl;

import cn.zealon.readingcloud.common.pojo.xzwresources.ReadBook;
import cn.zealon.readingcloud.book.dao.ReadBookDao;
import cn.zealon.readingcloud.book.service.ReadBookService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * 阅读图书表(ReadBook)表服务实现类
 *
 * @author makejava
 * @since 2023-04-17 09:28:16
 */
@Service("readBookService")
public class ReadBookServiceImpl implements ReadBookService {
    @Resource
    private ReadBookDao readBookDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ReadBook queryById(Long id) {
        return this.readBookDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param readBook    筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<ReadBook> queryByPage(ReadBook readBook, PageRequest pageRequest) {
        long total = this.readBookDao.count(readBook);
        return new PageImpl<>(this.readBookDao.queryAllByLimit(readBook, pageRequest), pageRequest, total);
    }

    public List<ReadBook> queryAll(ReadBook readBook){
        return this.readBookDao.queryAll(readBook);
    }

    /**
     * 新增数据
     *
     * @param readBook 实例对象
     * @return 实例对象
     */
    @Override
    public ReadBook insert(ReadBook readBook) {
        this.readBookDao.insert(readBook);
        return readBook;
    }

    /**
     * 修改数据
     *
     * @param readBook 实例对象
     * @return 实例对象
     */
    @Override
    public ReadBook update(ReadBook readBook) {
        this.readBookDao.update(readBook);
        return this.queryById(readBook.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.readBookDao.deleteById(id) > 0;
    }
}
