package cn.zealon.readingcloud.book.service;

import cn.zealon.readingcloud.common.pojo.xzwresources.Circle;
import cn.zealon.readingcloud.common.pojo.xzwusers.UAttribute;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 圈子表(Circle)表服务接口
 *
 * @author makejava
 * @since 2023-03-08 17:40:20
 */
public interface CircleService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Circle queryById(Long id);

    /**
     * 分页查询
     *
     * @param circle      筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<Circle> queryByPage(Circle circle, PageRequest pageRequest);

    List<Circle> queryAll(Circle circle);

    void doCircle (UAttribute uAttribute, String content, Long compositionId, Integer type);

    List<Circle>queryByUserId(Long userId);
    /**
     * 新增数据
     *
     * @param circle 实例对象
     * @return 实例对象
     */
    Circle insert(Circle circle);

    /**
     * 修改数据
     *
     * @param circle 实例对象
     * @return 实例对象
     */
    Circle update(Circle circle);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
