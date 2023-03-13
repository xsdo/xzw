package cn.zealon.readingcloud.homepage.service.impl;

import cn.zealon.readingcloud.common.pojo.xzwtasks.SuggestTask;
import cn.zealon.readingcloud.homepage.dao.SuggestTaskDao;
import cn.zealon.readingcloud.homepage.service.SuggestTaskService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * 推荐作文表(SuggestTask)表服务实现类
 *
 * @author makejava
 * @since 2023-03-01 11:11:43
 */
@Service("suggestTaskService")
public class SuggestTaskServiceImpl implements SuggestTaskService {
    @Resource
    private SuggestTaskDao suggestTaskDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SuggestTask queryById(Long id) {
        return this.suggestTaskDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param suggestTask 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<SuggestTask> queryByPage(SuggestTask suggestTask, PageRequest pageRequest) {
        long total = this.suggestTaskDao.count(suggestTask);
        return new PageImpl<>(this.suggestTaskDao.queryAllByLimit(suggestTask, pageRequest), pageRequest, total);
    }

    @Override
    public List<SuggestTask>queryAll(SuggestTask suggestTask){
        return this.suggestTaskDao.queryAll(suggestTask);
    }
    /**
     * 新增数据
     *
     * @param suggestTask 实例对象
     * @return 实例对象
     */
    @Override
    public SuggestTask insert(SuggestTask suggestTask) {
        this.suggestTaskDao.insert(suggestTask);
        return suggestTask;
    }

    /**
     * 修改数据
     *
     * @param suggestTask 实例对象
     * @return 实例对象
     */
    @Override
    public SuggestTask update(SuggestTask suggestTask) {
        this.suggestTaskDao.update(suggestTask);
        return this.queryById(suggestTask.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.suggestTaskDao.deleteById(id) > 0;
    }
}
