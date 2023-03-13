package cn.zealon.readingcloud.homepage.dao;

import cn.zealon.readingcloud.common.pojo.xzwtasks.SuggestUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 推荐用户表(SuggestUser)表数据库访问层
 *
 * @author makejava
 * @since 2023-03-01 11:11:39
 */
public interface SuggestUserDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SuggestUser queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param suggestUser 查询条件
     * @param pageable    分页对象
     * @return 对象列表
     */
    List<SuggestUser> queryAllByLimit(SuggestUser suggestUser, @Param("pageable") Pageable pageable);
    List<SuggestUser> queryAll(SuggestUser suggestUser);

    /**
     * 统计总行数
     *
     * @param suggestUser 查询条件
     * @return 总行数
     */
    long count(SuggestUser suggestUser);

    /**
     * 新增数据
     *
     * @param suggestUser 实例对象
     * @return 影响行数
     */
    int insert(SuggestUser suggestUser);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SuggestUser> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SuggestUser> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SuggestUser> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SuggestUser> entities);

    /**
     * 修改数据
     *
     * @param suggestUser 实例对象
     * @return 影响行数
     */
    int update(SuggestUser suggestUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

