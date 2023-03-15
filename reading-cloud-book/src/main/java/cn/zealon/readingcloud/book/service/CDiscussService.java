package cn.zealon.readingcloud.book.service;

import cn.zealon.readingcloud.book.vo.DiscussComVO;
import cn.zealon.readingcloud.book.vo.DiscussUserVO;
import cn.zealon.readingcloud.common.pojo.xzwresources.CDiscuss;
import com.alibaba.fastjson.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 评论表(CDiscuss)表服务接口
 *
 * @author makejava
 * @since 2023-03-01 11:13:54
 */
public interface CDiscussService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    CDiscuss queryById(Long id);

    /**
     * 分页查询
     *
     * @param cDiscuss    筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<CDiscuss> queryByPage(CDiscuss cDiscuss, PageRequest pageRequest);

    List<CDiscuss> queryAll(CDiscuss cDiscuss);
    List<DiscussComVO>queryByUserId(Long userId);

    List<DiscussUserVO>queryByCompositionId(Long compositionId,String remarks);

    JSONObject doDiscuss(Long userId, String discuss, Long compositionId,Integer type);
    /**
     * 新增数据
     *
     * @param cDiscuss 实例对象
     * @return 实例对象
     */
    CDiscuss insert(CDiscuss cDiscuss);

    /**
     * 修改数据
     *
     * @param cDiscuss 实例对象
     * @return 实例对象
     */
    CDiscuss update(CDiscuss cDiscuss);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
