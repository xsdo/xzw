package cn.zealon.readingcloud.account.dao;

import cn.zealon.readingcloud.common.pojo.xzwusers.UFans;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 用户粉丝表(UFans)表数据库访问层
 *
 * @author makejava
 * @since 2023-03-01 10:43:53
 */
public interface UFansDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UFans queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param uFans    查询条件
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<UFans> queryAllByLimit(UFans uFans, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param uFans 查询条件
     * @return 总行数
     */
    long count(UFans uFans);

    /**
     * 新增数据
     *
     * @param uFans 实例对象
     * @return 影响行数
     */
    int insert(UFans uFans);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<UFans> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<UFans> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<UFans> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<UFans> entities);

    /**
     * 修改数据
     *
     * @param uFans 实例对象
     * @return 影响行数
     */
    int update(UFans uFans);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

