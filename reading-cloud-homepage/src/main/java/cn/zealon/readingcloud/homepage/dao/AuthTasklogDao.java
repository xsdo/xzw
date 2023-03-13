package cn.zealon.readingcloud.homepage.dao;

import cn.zealon.readingcloud.common.pojo.xzwtasks.AuthTasklog;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 认证用户记录表(AuthTasklog)表数据库访问层
 *
 * @author makejava
 * @since 2023-03-01 11:11:42
 */
public interface AuthTasklogDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AuthTasklog queryById(Long id);

//    int count updateAll(AuthTasklog authTasklog);
    /**
     * 查询指定行数据
     *
     * @param authTasklog 查询条件
     * @param pageable    分页对象
     * @return 对象列表
     */
    List<AuthTasklog> queryAllByLimit(AuthTasklog authTasklog, @Param("pageable") Pageable pageable);
    List<AuthTasklog> queryAll(AuthTasklog authTasklog);

    /**
     * 统计总行数
     *
     * @param authTasklog 查询条件
     * @return 总行数
     */
    long count(AuthTasklog authTasklog);

    /**
     * 新增数据
     *
     * @param authTasklog 实例对象
     * @return 影响行数
     */
    int insert(AuthTasklog authTasklog);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<AuthTasklog> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<AuthTasklog> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<AuthTasklog> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<AuthTasklog> entities);

    /**
     * 修改数据
     *
     * @param authTasklog 实例对象
     * @return 影响行数
     */
    int update(AuthTasklog authTasklog);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

