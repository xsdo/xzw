package cn.zealon.readingcloud.book.service.impl;

import cn.zealon.readingcloud.common.pojo.xzwresources.ReadEssay;
import cn.zealon.readingcloud.book.dao.ReadEssayDao;
import cn.zealon.readingcloud.book.service.ReadEssayService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * 阅读文章表(ReadEssay)表服务实现类
 *
 * @author makejava
 * @since 2023-04-17 09:28:15
 */
@Service("readEssayService")
public class ReadEssayServiceImpl implements ReadEssayService {
    @Resource
    private ReadEssayDao readEssayDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ReadEssay queryById(Long id) {
        return this.readEssayDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param readEssay   筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<ReadEssay> queryByPage(ReadEssay readEssay, PageRequest pageRequest) {
        long total = this.readEssayDao.count(readEssay);
        return new PageImpl<>(this.readEssayDao.queryAllByLimit(readEssay, pageRequest), pageRequest, total);
    }

    @Override
    public List<ReadEssay>queryAll(ReadEssay readEssay) {
        return this.readEssayDao.queryAll(readEssay);
    }

    /**
     * 新增数据
     *
     * @param readEssay 实例对象
     * @return 实例对象
     */
    @Override
    public ReadEssay insert(ReadEssay readEssay) {
        this.readEssayDao.insert(readEssay);
        return readEssay;
    }

    /**
     * 修改数据
     *
     * @param readEssay 实例对象
     * @return 实例对象
     */
    @Override
    public ReadEssay update(ReadEssay readEssay) {
        this.readEssayDao.update(readEssay);
        return this.queryById(readEssay.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.readEssayDao.deleteById(id) > 0;
    }
}
