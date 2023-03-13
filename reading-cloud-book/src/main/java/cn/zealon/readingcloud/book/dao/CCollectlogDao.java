package cn.zealon.readingcloud.book.dao;

import cn.zealon.readingcloud.common.pojo.xzwresources.CCollectlog;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 收藏夹文件表(CCollectlog)表数据库访问层
 *
 * @author makejava
 * @since 2023-03-10 17:51:36
 */
public interface CCollectlogDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    CCollectlog queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param cCollectlog 查询条件
     * @param pageable    分页对象
     * @return 对象列表
     */
    List<CCollectlog> queryAllByLimit(CCollectlog cCollectlog, @Param("pageable") Pageable pageable);
    List<CCollectlog> queryAll(CCollectlog cCollectlog);

    /**
     * 统计总行数
     *
     * @param cCollectlog 查询条件
     * @return 总行数
     */
    long count(CCollectlog cCollectlog);

    /**
     * 新增数据
     *
     * @param cCollectlog 实例对象
     * @return 影响行数
     */
    int insert(CCollectlog cCollectlog);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<CCollectlog> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<CCollectlog> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<CCollectlog> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<CCollectlog> entities);

    /**
     * 修改数据
     *
     * @param cCollectlog 实例对象
     * @return 影响行数
     */
    int update(CCollectlog cCollectlog);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

