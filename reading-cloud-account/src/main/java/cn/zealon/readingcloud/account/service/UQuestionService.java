package cn.zealon.readingcloud.account.service;

import cn.zealon.readingcloud.common.pojo.xzwusers.UQuestion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 问题反馈表(UQuestion)表服务接口
 *
 * @author makejava
 * @since 2023-03-10 17:54:35
 */
public interface UQuestionService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UQuestion queryById(Long id);

    /**
     * 分页查询
     *
     * @param uQuestion   筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<UQuestion> queryByPage(UQuestion uQuestion, PageRequest pageRequest);


    List<UQuestion> queryAll(UQuestion uQuestion);
    /**
     * 新增数据
     *
     * @param uQuestion 实例对象
     * @return 实例对象
     */
    UQuestion insert(UQuestion uQuestion);

    /**
     * 修改数据
     *
     * @param uQuestion 实例对象
     * @return 实例对象
     */
    UQuestion update(UQuestion uQuestion);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
