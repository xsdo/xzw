package cn.zealon.readingcloud.book.service.impl;

import cn.zealon.readingcloud.book.dao.ReadEnrollDao;
import cn.zealon.readingcloud.book.service.ReadEnrollService;
import cn.zealon.readingcloud.common.pojo.xzwresources.ReadEnroll;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * 活动报名表(ReadEnroll)表服务实现类
 *
 * @author makejava
 * @since 2023-04-17 09:28:20
 */
@Service("readEnrollService")
public class ReadEnrollServiceImpl implements ReadEnrollService {
    @Resource
    private ReadEnrollDao readEnrollDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ReadEnroll queryById(Long id) {
        return this.readEnrollDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param readEnroll  筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<ReadEnroll> queryByPage(ReadEnroll readEnroll, PageRequest pageRequest) {
        long total = this.readEnrollDao.count(readEnroll);
        return new PageImpl<>(this.readEnrollDao.queryAllByLimit(readEnroll, pageRequest), pageRequest, total);
    }

    @Override
    public List<ReadEnroll>queryAll(ReadEnroll readEnroll) {
        return this.readEnrollDao.queryAll(readEnroll);
    }

    /**
     * 新增数据
     *
     * @param readEnroll 实例对象
     * @return 实例对象
     */
    @Override
    public ReadEnroll insert(ReadEnroll readEnroll) {
        this.readEnrollDao.insert(readEnroll);
        return readEnroll;
    }

    /**
     * 修改数据
     *
     * @param readEnroll 实例对象
     * @return 实例对象
     */
    @Override
    public ReadEnroll update(ReadEnroll readEnroll) {
        this.readEnrollDao.update(readEnroll);
        return this.queryById(readEnroll.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.readEnrollDao.deleteById(id) > 0;
    }
}
