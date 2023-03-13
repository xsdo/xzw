package cn.zealon.readingcloud.account.service;

import cn.zealon.readingcloud.common.pojo.xzwusers.UVip;
import com.alibaba.fastjson.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 用户会员表(UVip)表服务接口
 *
 * @author makejava
 * @since 2023-03-01 10:43:56
 */
public interface UVipService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UVip queryById(Long id);

    /**
     * 分页查询
     *
     * @param uVip        筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<UVip> queryByPage(UVip uVip, PageRequest pageRequest);

    List<UVip> queryAll(UVip uVip);

    JSONObject queryVipByUserId(Long userId);

    void toVipFirst(Long userId);
    /**
     * 新增数据
     *
     * @param uVip 实例对象
     * @return 实例对象
     */
    UVip insert(UVip uVip);

    /**
     * 修改数据
     *
     * @param uVip 实例对象
     * @return 实例对象
     */
    UVip update(UVip uVip);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
