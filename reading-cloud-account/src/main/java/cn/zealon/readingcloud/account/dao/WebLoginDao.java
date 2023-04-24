package cn.zealon.readingcloud.account.dao;

import cn.zealon.readingcloud.common.pojo.xzwusers.WebLogin;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 扫码登录表(WebLogin)表数据库访问层
 *
 * @author makejava
 * @since 2023-04-18 13:38:36
 */
public interface WebLoginDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    WebLogin queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param webLogin 查询条件
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<WebLogin> queryAllByLimit(WebLogin webLogin, @Param("pageable") Pageable pageable);
    List<WebLogin> queryAll(WebLogin webLogin);

    /**
     * 统计总行数
     *
     * @param webLogin 查询条件
     * @return 总行数
     */
    long count(WebLogin webLogin);

    /**
     * 新增数据
     *
     * @param webLogin 实例对象
     * @return 影响行数
     */
    int insert(WebLogin webLogin);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<WebLogin> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<WebLogin> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<WebLogin> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<WebLogin> entities);

    /**
     * 修改数据
     *
     * @param webLogin 实例对象
     * @return 影响行数
     */
    int update(WebLogin webLogin);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

