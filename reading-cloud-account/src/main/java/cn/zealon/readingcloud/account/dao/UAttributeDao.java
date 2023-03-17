package cn.zealon.readingcloud.account.dao;

import cn.zealon.readingcloud.common.pojo.xzwresources.Composition;
import cn.zealon.readingcloud.common.pojo.xzwusers.UAttribute;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 用户属性表(UAttribute)表数据库访问层
 *
 * @author makejava
 * @since 2023-03-06 09:23:20
 */
public interface UAttributeDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UAttribute queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param uAttribute 查询条件
     * @param pageable   分页对象
     * @return 对象列表
     */
    List<UAttribute> queryAllByLimit(UAttribute uAttribute, @Param("pageable") Pageable pageable);
    List<UAttribute> queryAll(UAttribute uAttribute);


    List<UAttribute>queryRand(int size);
    /**
     * 统计总行数
     *
     * @param uAttribute 查询条件
     * @return 总行数
     */
    long count(UAttribute uAttribute);

    /**
     * 新增数据
     *
     * @param uAttribute 实例对象
     * @return 影响行数
     */
    int insert(UAttribute uAttribute);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<UAttribute> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<UAttribute> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<UAttribute> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<UAttribute> entities);

    /**
     * 修改数据
     *
     * @param uAttribute 实例对象
     * @return 影响行数
     */
    int update(UAttribute uAttribute);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

