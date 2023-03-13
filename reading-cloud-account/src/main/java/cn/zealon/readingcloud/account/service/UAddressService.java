package cn.zealon.readingcloud.account.service;

import cn.zealon.readingcloud.common.pojo.xzwusers.UAddress;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 用户地址表(UAddress)表服务接口
 *
 * @author makejava
 * @since 2023-03-10 17:54:34
 */
public interface UAddressService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UAddress queryById(Long id);

    /**
     * 分页查询
     *
     * @param uAddress    筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<UAddress> queryByPage(UAddress uAddress, PageRequest pageRequest);


    List<UAddress> queryAll(UAddress uAddress);

    UAddress queryByUserId(Long userId);
    /**
     * 新增数据
     *
     * @param uAddress 实例对象
     * @return 实例对象
     */
    UAddress insert(UAddress uAddress);

    /**
     * 修改数据
     *
     * @param uAddress 实例对象
     * @return 实例对象
     */
    UAddress update(UAddress uAddress);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
