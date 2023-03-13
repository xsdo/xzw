package cn.zealon.readingcloud.book.dao;

import cn.zealon.readingcloud.common.pojo.xzwresources.CRecitation;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 朗读者表(CRecitation)表数据库访问层
 *
 * @author makejava
 * @since 2023-03-01 11:13:57
 */
public interface CRecitationDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    CRecitation queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param cRecitation 查询条件
     * @param pageable    分页对象
     * @return 对象列表
     */
    List<CRecitation> queryAllByLimit(CRecitation cRecitation, @Param("pageable") Pageable pageable);
    List<CRecitation> queryAll(CRecitation cRecitation);

    /**
     * 统计总行数
     *
     * @param cRecitation 查询条件
     * @return 总行数
     */
    long count(CRecitation cRecitation);

    /**
     * 新增数据
     *
     * @param cRecitation 实例对象
     * @return 影响行数
     */
    int insert(CRecitation cRecitation);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<CRecitation> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<CRecitation> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<CRecitation> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<CRecitation> entities);

    /**
     * 修改数据
     *
     * @param cRecitation 实例对象
     * @return 影响行数
     */
    int update(CRecitation cRecitation);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

