package cn.zealon.readingcloud.account.dao;

import cn.zealon.readingcloud.common.pojo.xzwusers.UFlowers;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 鲜花表(UFlowers)表数据库访问层
 *
 * @author makejava
 * @since 2023-03-06 09:23:22
 */
public interface UFlowersDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UFlowers queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param uFlowers 查询条件
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<UFlowers> queryAllByLimit(UFlowers uFlowers, @Param("pageable") Pageable pageable);
    List<UFlowers> queryAll(UFlowers uFlowers);

    /**
     * 统计总行数
     *
     * @param uFlowers 查询条件
     * @return 总行数
     */
    long count(UFlowers uFlowers);

    /**
     * 新增数据
     *
     * @param uFlowers 实例对象
     * @return 影响行数
     */
    int insert(UFlowers uFlowers);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<UFlowers> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<UFlowers> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<UFlowers> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<UFlowers> entities);

    /**
     * 修改数据
     *
     * @param uFlowers 实例对象
     * @return 影响行数
     */
    int update(UFlowers uFlowers);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

