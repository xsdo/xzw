package cn.zealon.readingcloud.account.service;

import cn.zealon.readingcloud.common.pojo.xzwusers.UActivation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 激活码表(UActivation)表服务接口
 *
 * @author makejava
 * @since 2023-03-01 10:43:52
 */
public interface UActivationService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UActivation queryById(Long id);

    /**
     * 分页查询
     *
     * @param uActivation 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<UActivation> queryByPage(UActivation uActivation, PageRequest pageRequest);
    List<UActivation> queryAll(UActivation uActivation);
    /**
     * 新增数据
     *
     * @param uActivation 实例对象
     * @return 实例对象
     */
    UActivation insert(UActivation uActivation);

    /**
     * 修改数据
     *
     * @param uActivation 实例对象
     * @return 实例对象
     */
    UActivation update(UActivation uActivation);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
