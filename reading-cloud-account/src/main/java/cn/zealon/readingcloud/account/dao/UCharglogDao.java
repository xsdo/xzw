package cn.zealon.readingcloud.account.dao;

import cn.zealon.readingcloud.common.pojo.xzwusers.UCharglog;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 充值记录表(UCharglog)表数据库访问层
 *
 * @author makejava
 * @since 2023-03-10 17:54:34
 */
public interface UCharglogDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UCharglog queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param uCharglog 查询条件
     * @param pageable  分页对象
     * @return 对象列表
     */
    List<UCharglog> queryAllByLimit(UCharglog uCharglog, @Param("pageable") Pageable pageable);
    List<UCharglog> queryAll(UCharglog uCharglog);

    /**
     * 统计总行数
     *
     * @param uCharglog 查询条件
     * @return 总行数
     */
    long count(UCharglog uCharglog);

    /**
     * 新增数据
     *
     * @param uCharglog 实例对象
     * @return 影响行数
     */
    int insert(UCharglog uCharglog);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<UCharglog> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<UCharglog> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<UCharglog> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<UCharglog> entities);

    /**
     * 修改数据
     *
     * @param uCharglog 实例对象
     * @return 影响行数
     */
    int update(UCharglog uCharglog);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

