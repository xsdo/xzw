package cn.zealon.readingcloud.homepage.service;

import cn.zealon.readingcloud.common.pojo.xzwtasks.AuthTasklog;
import com.alibaba.fastjson.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 认证用户记录表(AuthTasklog)表服务接口
 *
 * @author makejava
 * @since 2023-03-01 11:11:43
 */
public interface AuthTasklogService {



    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AuthTasklog queryById(Long id);

    /**
     * 分页查询
     *
     * @param authTasklog 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<AuthTasklog> queryByPage(AuthTasklog authTasklog, PageRequest pageRequest);

    List<AuthTasklog> queryAll(AuthTasklog authTasklog);

    void resetTaskLog();

    List<AuthTasklog>queryByUserId(Long userId);

    JSONObject toAuthTasklog(Long authTasklogId);

    String getRedisTask(Long userId,Long taskId);
    void setRedisTask(Long userId,Long taskId);
    /**
     * 新增数据
     *
     * @param authTasklog 实例对象
     * @return 实例对象
     */
    AuthTasklog insert(AuthTasklog authTasklog);

    /**
     * 修改数据
     *
     * @param authTasklog 实例对象
     * @return 实例对象
     */
    AuthTasklog update(AuthTasklog authTasklog);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
