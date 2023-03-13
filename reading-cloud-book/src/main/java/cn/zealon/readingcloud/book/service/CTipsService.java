package cn.zealon.readingcloud.book.service;

import cn.zealon.readingcloud.common.pojo.xzwresources.CTips;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 举报表(CTips)表服务接口
 *
 * @author makejava
 * @since 2023-03-01 11:13:59
 */
public interface CTipsService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    CTips queryById(Long id);

    /**
     * 分页查询
     *
     * @param cTips       筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<CTips> queryByPage(CTips cTips, PageRequest pageRequest);
    List<CTips> queryAll(CTips cTips);

    /**
     * 新增数据
     *
     * @param cTips 实例对象
     * @return 实例对象
     */
    CTips insert(CTips cTips);

    /**
     * 修改数据
     *
     * @param cTips 实例对象
     * @return 实例对象
     */
    CTips update(CTips cTips);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
