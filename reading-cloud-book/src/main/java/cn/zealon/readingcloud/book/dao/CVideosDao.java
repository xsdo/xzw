package cn.zealon.readingcloud.book.dao;

import cn.zealon.readingcloud.common.pojo.xzwresources.CVideos;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 视频表(CVideos)表数据库访问层
 *
 * @author makejava
 * @since 2023-03-01 11:13:54
 */
public interface CVideosDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    CVideos queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param cVideos  查询条件
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<CVideos> queryAllByLimit(CVideos cVideos, @Param("pageable") Pageable pageable);

    List<CVideos> queryAll(CVideos cVideos);

    /**
     * 统计总行数
     *
     * @param cVideos 查询条件
     * @return 总行数
     */
    long count(CVideos cVideos);

    /**
     * 新增数据
     *
     * @param cVideos 实例对象
     * @return 影响行数
     */
    int insert(CVideos cVideos);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<CVideos> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<CVideos> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<CVideos> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<CVideos> entities);

    /**
     * 修改数据
     *
     * @param cVideos 实例对象
     * @return 影响行数
     */
    int update(CVideos cVideos);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

