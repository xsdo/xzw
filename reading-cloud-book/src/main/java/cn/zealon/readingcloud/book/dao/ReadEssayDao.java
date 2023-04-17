package cn.zealon.readingcloud.book.dao;

import cn.zealon.readingcloud.common.pojo.xzwresources.ReadEssay;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 阅读文章表(ReadEssay)表数据库访问层
 *
 * @author makejava
 * @since 2023-04-17 09:28:14
 */
public interface ReadEssayDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ReadEssay queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param readEssay 查询条件
     * @param pageable  分页对象
     * @return 对象列表
     */
    List<ReadEssay> queryAllByLimit(ReadEssay readEssay, @Param("pageable") Pageable pageable);
    List<ReadEssay> queryAll(ReadEssay readEssay);

    /**
     * 统计总行数
     *
     * @param readEssay 查询条件
     * @return 总行数
     */
    long count(ReadEssay readEssay);

    /**
     * 新增数据
     *
     * @param readEssay 实例对象
     * @return 影响行数
     */
    int insert(ReadEssay readEssay);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<ReadEssay> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<ReadEssay> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<ReadEssay> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<ReadEssay> entities);

    /**
     * 修改数据
     *
     * @param readEssay 实例对象
     * @return 影响行数
     */
    int update(ReadEssay readEssay);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

