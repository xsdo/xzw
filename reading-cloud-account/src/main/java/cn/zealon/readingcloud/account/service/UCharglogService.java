package cn.zealon.readingcloud.account.service;

import cn.zealon.readingcloud.common.pojo.xzwusers.UCharglog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 充值记录表(UCharglog)表服务接口
 *
 * @author makejava
 * @since 2023-03-10 17:54:34
 */
public interface UCharglogService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UCharglog queryById(Long id);

    /**
     * 分页查询
     *
     * @param uCharglog   筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<UCharglog> queryByPage(UCharglog uCharglog, PageRequest pageRequest);


    List<UCharglog> queryAll(UCharglog uCharglog);

    List<UCharglog>queryByUserId(Long userId);
    /**
     * 新增数据
     *
     * @param uCharglog 实例对象
     * @return 实例对象
     */
    UCharglog insert(UCharglog uCharglog);

    /**
     * 修改数据
     *
     * @param uCharglog 实例对象
     * @return 实例对象
     */
    UCharglog update(UCharglog uCharglog);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
