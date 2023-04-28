package cn.zealon.readingcloud.book.dao;

import cn.zealon.readingcloud.common.pojo.xzwresources.ChatComposition;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * chat作文表(ChatComposition)表数据库访问层
 *
 * @author makejava
 * @since 2023-04-25 15:18:22
 */
public interface ChatCompositionDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ChatComposition queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param chatComposition 查询条件
     * @param pageable        分页对象
     * @return 对象列表
     */
    List<ChatComposition> queryAllByLimit(ChatComposition chatComposition, @Param("pageable") Pageable pageable);
    List<ChatComposition> queryAll(ChatComposition chatComposition);

    /**
     * 统计总行数
     *
     * @param chatComposition 查询条件
     * @return 总行数
     */
    long count(ChatComposition chatComposition);

    /**
     * 新增数据
     *
     * @param chatComposition 实例对象
     * @return 影响行数
     */
    int insert(ChatComposition chatComposition);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<ChatComposition> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<ChatComposition> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<ChatComposition> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<ChatComposition> entities);

    /**
     * 修改数据
     *
     * @param chatComposition 实例对象
     * @return 影响行数
     */
    int update(ChatComposition chatComposition);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

