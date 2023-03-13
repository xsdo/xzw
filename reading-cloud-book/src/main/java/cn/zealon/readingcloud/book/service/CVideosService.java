package cn.zealon.readingcloud.book.service;

import cn.zealon.readingcloud.common.pojo.xzwresources.CVideos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 视频表(CVideos)表服务接口
 *
 * @author makejava
 * @since 2023-03-01 11:13:55
 */
public interface CVideosService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    CVideos queryById(Long id);

    /**
     * 分页查询
     *
     * @param cVideos     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<CVideos> queryByPage(CVideos cVideos, PageRequest pageRequest);

    List<CVideos> queryAll(CVideos cVideos);

    /**
     * 新增数据
     *
     * @param cVideos 实例对象
     * @return 实例对象
     */
    CVideos insert(CVideos cVideos);

    /**
     * 修改数据
     *
     * @param cVideos 实例对象
     * @return 实例对象
     */
    CVideos update(CVideos cVideos);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
