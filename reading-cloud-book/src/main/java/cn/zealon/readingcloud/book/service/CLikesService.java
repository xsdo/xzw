package cn.zealon.readingcloud.book.service;

import cn.zealon.readingcloud.common.pojo.xzwresources.CLikes;
import com.alibaba.fastjson.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 用户点赞表(CLikes)表服务接口
 *
 * @author makejava
 * @since 2023-03-09 10:51:23
 */
public interface CLikesService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    CLikes queryById(Long id);

    /**
     * 分页查询
     *
     * @param cLikes      筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<CLikes> queryByPage(CLikes cLikes, PageRequest pageRequest);

    List<CLikes> queryAll(Long userId);

    Integer checkLikes (Long userId ,Long likesId);


    JSONObject toLikes(Long userId , Long likesId, Integer type);

    void setRedisTask(Long userId,Long taskId);
    /**
     * 新增数据
     *
     * @param cLikes 实例对象
     * @return 实例对象
     */
    CLikes insert(CLikes cLikes);

    /**
     * 修改数据
     *
     * @param cLikes 实例对象
     * @return 实例对象
     */
    CLikes update(CLikes cLikes);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
