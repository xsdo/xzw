package cn.zealon.readingcloud.account.dao;

import cn.zealon.readingcloud.common.pojo.xzwusers.UVip;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 用户会员表(UVip)表数据库访问层
 *
 * @author makejava
 * @since 2023-03-01 10:43:56
 */
public interface UVipDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UVip queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param uVip     查询条件
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<UVip> queryAllByLimit(UVip uVip, @Param("pageable") Pageable pageable);


    List<UVip> queryAll(UVip uVip);

    /**
     * 统计总行数
     *
     * @param uVip 查询条件
     * @return 总行数
     */
    long count(UVip uVip);

    /**
     * 新增数据
     *
     * @param uVip 实例对象
     * @return 影响行数
     */
    int insert(UVip uVip);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<UVip> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<UVip> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<UVip> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<UVip> entities);

    /**
     * 修改数据
     *
     * @param uVip 实例对象
     * @return 影响行数
     */
    int update(UVip uVip);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

