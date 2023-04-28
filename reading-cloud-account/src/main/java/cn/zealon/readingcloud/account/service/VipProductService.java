package cn.zealon.readingcloud.account.service;

import cn.zealon.readingcloud.common.pojo.xzwusers.VipProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 会员商品表(VipProduct)表服务接口
 *
 * @author makejava
 * @since 2023-04-26 15:58:16
 */
public interface VipProductService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    VipProduct queryById(Long id);

    /**
     * 分页查询
     *
     * @param vipProduct  筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<VipProduct> queryByPage(VipProduct vipProduct, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param vipProduct 实例对象
     * @return 实例对象
     */
    VipProduct insert(VipProduct vipProduct);


    List<VipProduct>queryAll(VipProduct vipProduct);
    /**
     * 修改数据
     *
     * @param vipProduct 实例对象
     * @return 实例对象
     */
    VipProduct update(VipProduct vipProduct);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
