package cn.zealon.readingcloud.book.service.impl;

import cn.zealon.readingcloud.common.pojo.xzwresources.ReadContest;
import cn.zealon.readingcloud.book.dao.ReadContestDao;
import cn.zealon.readingcloud.book.service.ReadContestService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * 活动参赛表(ReadContest)表服务实现类
 *
 * @author makejava
 * @since 2023-04-17 09:28:18
 */
@Service("readContestService")
public class ReadContestServiceImpl implements ReadContestService {
    @Resource
    private ReadContestDao readContestDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ReadContest queryById(Long id) {
        return this.readContestDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param readContest 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<ReadContest> queryByPage(ReadContest readContest, PageRequest pageRequest) {
        long total = this.readContestDao.count(readContest);
        return new PageImpl<>(this.readContestDao.queryAllByLimit(readContest, pageRequest), pageRequest, total);
    }

    @Override
    public List<ReadContest>queryAll(ReadContest readContest) {
        return this.readContestDao.queryAll(readContest);
    }

    /**
     * 新增数据
     *
     * @param readContest 实例对象
     * @return 实例对象
     */
    @Override
    public ReadContest insert(ReadContest readContest) {
        this.readContestDao.insert(readContest);
        return readContest;
    }

    /**
     * 修改数据
     *
     * @param readContest 实例对象
     * @return 实例对象
     */
    @Override
    public ReadContest update(ReadContest readContest) {
        this.readContestDao.update(readContest);
        return this.queryById(readContest.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.readContestDao.deleteById(id) > 0;
    }
}
