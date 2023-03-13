package cn.zealon.readingcloud.account.dao;

import cn.zealon.readingcloud.common.pojo.xzwusers.XzwUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 用户表(XzwUser)表数据库访问层
 *
 * @author makejava
 * @since 2023-03-01 10:43:57
 */
public interface XzwUserDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    XzwUser queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param xzwUser  查询条件
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<XzwUser> queryAllByLimit(XzwUser xzwUser, @Param("pageable") Pageable pageable);
    List<XzwUser> queryAll(XzwUser xzwUser);


    List<XzwUser> queryByPhoneNumber(String phoneNumber);

    List<XzwUser> queryByOpenId(String openId);

    /**
     * 统计总行数
     *
     * @param xzwUser 查询条件
     * @return 总行数
     */
    long count(XzwUser xzwUser);

    /**
     * 新增数据
     *
     * @param xzwUser 实例对象
     * @return 影响行数
     */
    int insert(XzwUser xzwUser);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<XzwUser> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<XzwUser> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<XzwUser> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<XzwUser> entities);

    /**
     * 修改数据
     *
     * @param xzwUser 实例对象
     * @return 影响行数
     */
    int update(XzwUser xzwUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

