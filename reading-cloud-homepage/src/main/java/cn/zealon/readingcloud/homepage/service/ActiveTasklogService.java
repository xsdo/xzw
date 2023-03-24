package cn.zealon.readingcloud.homepage.service;

import cn.zealon.readingcloud.common.pojo.xzwtasks.ActiveTasklog;
import com.alibaba.fastjson.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 活动用户记录表(ActiveTasklog)表服务接口
 *
 * @author makejava
 * @since 2023-03-24 13:46:37
 */
public interface ActiveTasklogService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ActiveTasklog queryById(Long id);

    /**
     * 分页查询
     *
     * @param activeTasklog 筛选条件
     * @param pageRequest   分页对象
     * @return 查询结果
     */
    Page<ActiveTasklog> queryByPage(ActiveTasklog activeTasklog, PageRequest pageRequest);

    List<ActiveTasklog> queryAll(ActiveTasklog activeTasklog);

    Integer checkEnter(Long userId,Long taskId);

    JSONObject toEnter(Long userId , Long taskId );
    /**
     * 新增数据
     *
     * @param activeTasklog 实例对象
     * @return 实例对象
     */
    ActiveTasklog insert(ActiveTasklog activeTasklog);

    /**
     * 修改数据
     *
     * @param activeTasklog 实例对象
     * @return 实例对象
     */
    ActiveTasklog update(ActiveTasklog activeTasklog);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
