package cn.zealon.readingcloud.account.dao;

import cn.zealon.readingcloud.common.pojo.xzwusers.USign;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 签到表(USign)表数据库访问层
 *
 * @author makejava
 * @since 2023-03-07 09:20:53
 */
public interface USignDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    USign queryById(Long id);

    List<USign>queryAll(USign uSign);

    /**
     * 查询指定行数据
     *
     * @param uSign    查询条件
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<USign> queryAllByLimit(USign uSign, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param uSign 查询条件
     * @return 总行数
     */
    long count(USign uSign);

    /**
     * 新增数据
     *
     * @param uSign 实例对象
     * @return 影响行数
     */
    int insert(USign uSign);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<USign> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<USign> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<USign> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<USign> entities);

    /**
     * 修改数据
     *
     * @param uSign 实例对象
     * @return 影响行数
     */
    int update(USign uSign);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

