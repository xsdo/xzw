package cn.zealon.readingcloud.book.service;

import cn.zealon.readingcloud.common.pojo.xzwresources.CCollectlog;
import com.alibaba.fastjson.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 收藏夹文件表(CCollectlog)表服务接口
 *
 * @author makejava
 * @since 2023-03-10 17:51:36
 */
public interface CCollectlogService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    CCollectlog queryById(Long id);

    /**
     * 分页查询
     *
     * @param cCollectlog 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<CCollectlog> queryByPage(CCollectlog cCollectlog, PageRequest pageRequest);

    List<CCollectlog> queryAll(CCollectlog cCollectlog);
    /**
     * 新增数据
     *
     * @param cCollectlog 实例对象
     * @return 实例对象
     */
    CCollectlog insert(CCollectlog cCollectlog);

    JSONObject addCollectlog (Long collectId, String cName , String cImage, Long compositionId, int type);

    /**
     * 修改数据
     *
     * @param cCollectlog 实例对象
     * @return 实例对象
     */
    CCollectlog update(CCollectlog cCollectlog);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
