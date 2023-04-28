package cn.zealon.readingcloud.book.service;

import cn.zealon.readingcloud.common.pojo.xzwresources.HotWords;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 热搜表(HotWords)表服务接口
 *
 * @author makejava
 * @since 2023-04-27 15:24:35
 */
public interface HotWordsService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    HotWords queryById(Long id);

    /**
     * 分页查询
     *
     * @param hotWords    筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<HotWords> queryByPage(HotWords hotWords, PageRequest pageRequest);

    List<HotWords> queryAll(HotWords hotWords);

    void addHotWords(String words);


    void cleanLikes();

    List<HotWords>queryTop();

    List<HotWords>queryLikes();
    /**
     * 新增数据
     *
     * @param hotWords 实例对象
     * @return 实例对象
     */
    HotWords insert(HotWords hotWords);

    /**
     * 修改数据
     *
     * @param hotWords 实例对象
     * @return 实例对象
     */
    HotWords update(HotWords hotWords);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
