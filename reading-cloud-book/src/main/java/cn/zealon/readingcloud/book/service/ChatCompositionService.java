package cn.zealon.readingcloud.book.service;

import cn.zealon.readingcloud.common.pojo.xzwresources.ChatComposition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * chat作文表(ChatComposition)表服务接口
 *
 * @author makejava
 * @since 2023-04-25 15:18:24
 */
public interface ChatCompositionService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ChatComposition queryById(Long id);

    /**
     * 分页查询
     *
     * @param chatComposition 筛选条件
     * @param pageRequest     分页对象
     * @return 查询结果
     */
    Page<ChatComposition> queryByPage(ChatComposition chatComposition, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param chatComposition 实例对象
     * @return 实例对象
     */
    ChatComposition insert(ChatComposition chatComposition);


    Object getAnswer(Long userId,String grade ,String type ,int wordNumber,String keyword );

    List<ChatComposition> queryByUserId(Long userId);
    /**
     * 修改数据
     *
     * @param chatComposition 实例对象
     * @return 实例对象
     */
    ChatComposition update(ChatComposition chatComposition);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
