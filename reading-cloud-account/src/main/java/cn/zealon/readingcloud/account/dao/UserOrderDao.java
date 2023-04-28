package cn.zealon.readingcloud.account.dao;

import cn.zealon.readingcloud.common.pojo.xzwusers.UserOrder;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 订单表(UserOrder)表数据库访问层
 *
 * @author makejava
 * @since 2023-04-26 17:38:03
 */
public interface UserOrderDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserOrder queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param userOrder 查询条件
     * @param pageable  分页对象
     * @return 对象列表
     */
    List<UserOrder> queryAllByLimit(UserOrder userOrder, @Param("pageable") Pageable pageable);
    List<UserOrder> queryAll(UserOrder userOrder);

    /**
     * 统计总行数
     *
     * @param userOrder 查询条件
     * @return 总行数
     */
    long count(UserOrder userOrder);

    /**
     * 新增数据
     *
     * @param userOrder 实例对象
     * @return 影响行数
     */
    int insert(UserOrder userOrder);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<UserOrder> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<UserOrder> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<UserOrder> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<UserOrder> entities);

    /**
     * 修改数据
     *
     * @param userOrder 实例对象
     * @return 影响行数
     */
    int update(UserOrder userOrder);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

