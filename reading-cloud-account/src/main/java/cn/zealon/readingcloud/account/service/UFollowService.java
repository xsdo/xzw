package cn.zealon.readingcloud.account.service;

import cn.zealon.readingcloud.common.pojo.xzwusers.UFollow;
import com.alibaba.fastjson.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 用户关注表(UFollow)表服务接口
 *
 * @author makejava
 * @since 2023-03-01 10:43:54
 */
public interface UFollowService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UFollow queryById(Long id);

    Integer checkFollow(Long userId, Long followId);

    JSONObject doFollow(Long userId, Long followId);

    JSONObject queryFollowAll(Long userId);

    JSONObject queryFansAll(Long followId);

    /**
     * 分页查询
     *
     * @param uFollow     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<UFollow> queryByPage(UFollow uFollow, PageRequest pageRequest);

    UFollow queryByUserId(Long userId,Long followId);

    List<UFollow> queryFollow(Long userId);

    List<UFollow> queryFans(Long followId);
    /**
     * 新增数据
     *
     * @param uFollow 实例对象
     * @return 实例对象
     */
    UFollow insert(UFollow uFollow);

    /**
     * 修改数据
     *
     * @param uFollow 实例对象
     * @return 实例对象
     */
    UFollow update(UFollow uFollow);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
