package cn.zealon.readingcloud.homepage.service.impl;

import cn.zealon.readingcloud.common.pojo.xzwtasks.ReadTasklog;
import cn.zealon.readingcloud.homepage.dao.ReadTasklogDao;
import cn.zealon.readingcloud.homepage.service.ReadTasklogService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * 阅读任务记录表(ReadTasklog)表服务实现类
 *
 * @author makejava
 * @since 2023-03-01 11:11:40
 */
@Service("readTasklogService")
public class ReadTasklogServiceImpl implements ReadTasklogService {
    @Resource
    private ReadTasklogDao readTasklogDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ReadTasklog queryById(Long id) {
        return this.readTasklogDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param readTasklog 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<ReadTasklog> queryByPage(ReadTasklog readTasklog, PageRequest pageRequest) {
        long total = this.readTasklogDao.count(readTasklog);
        return new PageImpl<>(this.readTasklogDao.queryAllByLimit(readTasklog, pageRequest), pageRequest, total);
    }

    @Override
    public List<ReadTasklog>queryAllByUserId(Long userId){
        ReadTasklog readTasklog=new ReadTasklog();
        readTasklog.setUserId(userId);
        return this.readTasklogDao.queryAll(readTasklog);
    }
    /**
     * 新增数据
     *
     * @param readTasklog 实例对象
     * @return 实例对象
     */
    @Override
    public ReadTasklog insert(ReadTasklog readTasklog) {
        this.readTasklogDao.insert(readTasklog);
        return readTasklog;
    }

    /**
     * 修改数据
     *
     * @param readTasklog 实例对象
     * @return 实例对象
     */
    @Override
    public ReadTasklog update(ReadTasklog readTasklog) {
        this.readTasklogDao.update(readTasklog);
        return this.queryById(readTasklog.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.readTasklogDao.deleteById(id) > 0;
    }
}
