package cn.zealon.readingcloud.account.service;

import cn.zealon.readingcloud.common.pojo.xzwusers.PayInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 支付信息表(PayInfo)表服务接口
 *
 * @author makejava
 * @since 2023-04-26 15:58:15
 */
public interface PayInfoService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    PayInfo queryById(Long id);

    /**
     * 分页查询
     *
     * @param payInfo     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<PayInfo> queryByPage(PayInfo payInfo, PageRequest pageRequest);


    List<PayInfo> queryAll(PayInfo payInfo);
    /**
     * 新增数据
     *
     * @param payInfo 实例对象
     * @return 实例对象
     */
    PayInfo insert(PayInfo payInfo);

    /**
     * 修改数据
     *
     * @param payInfo 实例对象
     * @return 实例对象
     */
    PayInfo update(PayInfo payInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
