package cn.zealon.readingcloud.account.dao;

import cn.zealon.readingcloud.common.pojo.xzwusers.VipProduct;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 会员商品表(VipProduct)表数据库访问层
 *
 * @author makejava
 * @since 2023-04-26 15:58:16
 */
public interface VipProductDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    VipProduct queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param vipProduct 查询条件
     * @param pageable   分页对象
     * @return 对象列表
     */
    List<VipProduct> queryAllByLimit(VipProduct vipProduct, @Param("pageable") Pageable pageable);
    List<VipProduct> queryAll(VipProduct vipProduct);

    /**
     * 统计总行数
     *
     * @param vipProduct 查询条件
     * @return 总行数
     */
    long count(VipProduct vipProduct);

    /**
     * 新增数据
     *
     * @param vipProduct 实例对象
     * @return 影响行数
     */
    int insert(VipProduct vipProduct);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<VipProduct> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<VipProduct> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<VipProduct> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<VipProduct> entities);

    /**
     * 修改数据
     *
     * @param vipProduct 实例对象
     * @return 影响行数
     */
    int update(VipProduct vipProduct);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

