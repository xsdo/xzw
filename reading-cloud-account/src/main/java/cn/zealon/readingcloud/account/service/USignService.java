package cn.zealon.readingcloud.account.service;

import cn.zealon.readingcloud.common.pojo.xzwusers.USign;
import cn.zealon.readingcloud.common.pojo.xzwusers.USigndetail;
import com.alibaba.fastjson.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Map;

/**
 * 签到表(USign)表服务接口
 *
 * @author makejava
 * @since 2023-03-07 09:20:54
 */
public interface USignService {



    USigndetail todayDetail(Long userId);
    USigndetail yesterdayDetail(Long userId);


    JSONObject sign (Long userId);
    Map<String,Object> getSign(Long userId);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    USign queryById(Long id);

    USign queryByUserId(Long userId);

    /**
     * 分页查询
     *
     * @param uSign       筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<USign> queryByPage(USign uSign, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param uSign 实例对象
     * @return 实例对象
     */
    USign insert(USign uSign);

    /**
     * 修改数据
     *
     * @param uSign 实例对象
     * @return 实例对象
     */
    USign update(USign uSign);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
