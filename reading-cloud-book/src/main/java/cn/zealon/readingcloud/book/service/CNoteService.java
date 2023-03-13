package cn.zealon.readingcloud.book.service;

import cn.zealon.readingcloud.common.pojo.xzwresources.CNote;
import com.alibaba.fastjson.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 笔记表(CNote)表服务接口
 *
 * @author makejava
 * @since 2023-03-08 14:27:23
 */
public interface CNoteService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    CNote queryById(Long id);

    /**
     * 分页查询
     *
     * @param cNote       筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<CNote> queryByPage(CNote cNote, PageRequest pageRequest);
    List<CNote> queryAll(CNote cNote);

    JSONObject doNode(Long userId, String note);
    /**
     * 新增数据
     *
     * @param cNote 实例对象
     * @return 实例对象
     */
    CNote insert(CNote cNote);

    /**
     * 修改数据
     *
     * @param cNote 实例对象
     * @return 实例对象
     */
    CNote update(CNote cNote);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
