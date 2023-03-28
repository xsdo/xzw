package cn.zealon.readingcloud.account.service;

import cn.zealon.readingcloud.common.pojo.xzwusers.UTable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户标签表(UTable)表服务接口
 *
 * @author makejava
 * @since 2023-03-01 10:43:55
 */
public interface UTableService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UTable queryById(Long id);

    void toTableAdd(Long userId,Long tableId);


    List<UTable>queryByIds(ArrayList ids);

    /**
     * 分页查询
     *
     * @param uTable      筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<UTable> queryByPage(UTable uTable, PageRequest pageRequest);

    List<UTable> queryAll(UTable uTable);

    /**
     * 新增数据
     *
     * @param uTable 实例对象
     * @return 实例对象
     */
    UTable insert(UTable uTable);

    /**
     * 修改数据
     *
     * @param uTable 实例对象
     * @return 实例对象
     */
    UTable update(UTable uTable);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
