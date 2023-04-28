package cn.zealon.readingcloud.account.dao;

import cn.zealon.readingcloud.common.pojo.xzwusers.PayInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 支付信息表(PayInfo)表数据库访问层
 *
 * @author makejava
 * @since 2023-04-26 15:58:13
 */
public interface PayInfoDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    PayInfo queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param payInfo  查询条件
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<PayInfo> queryAllByLimit(PayInfo payInfo, @Param("pageable") Pageable pageable);
    List<PayInfo> queryAll(PayInfo payInfo);

    /**
     * 统计总行数
     *
     * @param payInfo 查询条件
     * @return 总行数
     */
    long count(PayInfo payInfo);

    /**
     * 新增数据
     *
     * @param payInfo 实例对象
     * @return 影响行数
     */
    int insert(PayInfo payInfo);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<PayInfo> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<PayInfo> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<PayInfo> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<PayInfo> entities);

    /**
     * 修改数据
     *
     * @param payInfo 实例对象
     * @return 影响行数
     */
    int update(PayInfo payInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

