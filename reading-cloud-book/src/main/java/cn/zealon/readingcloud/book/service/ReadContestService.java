package cn.zealon.readingcloud.book.service;

import cn.zealon.readingcloud.common.pojo.xzwresources.ReadContest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 活动参赛表(ReadContest)表服务接口
 *
 * @author makejava
 * @since 2023-04-17 09:28:18
 */
public interface ReadContestService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ReadContest queryById(Long id);

    /**
     * 分页查询
     *
     * @param readContest 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<ReadContest> queryByPage(ReadContest readContest, PageRequest pageRequest);

    List<ReadContest> queryAll(ReadContest readContest);
    /**
     * 新增数据
     *
     * @param readContest 实例对象
     * @return 实例对象
     */
    ReadContest insert(ReadContest readContest);

    /**
     * 修改数据
     *
     * @param readContest 实例对象
     * @return 实例对象
     */
    ReadContest update(ReadContest readContest);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
