package cn.zealon.readingcloud.book.dao;

import cn.zealon.readingcloud.common.pojo.xzwresources.CMuted;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 禁言表(CMuted)表数据库访问层
 *
 * @author makejava
 * @since 2023-03-01 11:14:01
 */
public interface CMutedDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    CMuted queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param cMuted   查询条件
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<CMuted> queryAllByLimit(CMuted cMuted, @Param("pageable") Pageable pageable);
    List<CMuted> queryAll(CMuted cMuted);

    /**
     * 统计总行数
     *
     * @param cMuted 查询条件
     * @return 总行数
     */
    long count(CMuted cMuted);

    /**
     * 新增数据
     *
     * @param cMuted 实例对象
     * @return 影响行数
     */
    int insert(CMuted cMuted);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<CMuted> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<CMuted> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<CMuted> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<CMuted> entities);

    /**
     * 修改数据
     *
     * @param cMuted 实例对象
     * @return 影响行数
     */
    int update(CMuted cMuted);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

