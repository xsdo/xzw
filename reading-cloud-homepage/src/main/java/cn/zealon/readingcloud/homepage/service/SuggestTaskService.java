package cn.zealon.readingcloud.homepage.service;

import cn.zealon.readingcloud.common.pojo.xzwtasks.SuggestTask;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 推荐作文表(SuggestTask)表服务接口
 *
 * @author makejava
 * @since 2023-03-01 11:11:43
 */
public interface SuggestTaskService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SuggestTask queryById(Long id);

    /**
     * 分页查询
     *
     * @param suggestTask 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<SuggestTask> queryByPage(SuggestTask suggestTask, PageRequest pageRequest);

    List<SuggestTask> queryAll(SuggestTask suggestTask);
    /**
     * 新增数据
     *
     * @param suggestTask 实例对象
     * @return 实例对象
     */
    SuggestTask insert(SuggestTask suggestTask);

    /**
     * 修改数据
     *
     * @param suggestTask 实例对象
     * @return 实例对象
     */
    SuggestTask update(SuggestTask suggestTask);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
