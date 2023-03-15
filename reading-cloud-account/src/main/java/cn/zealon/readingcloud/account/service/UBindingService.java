package cn.zealon.readingcloud.account.service;

import cn.zealon.readingcloud.common.pojo.xzwusers.UBinding;
import com.alibaba.fastjson.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 绑定班级表(UBinding)表服务接口
 *
 * @author makejava
 * @since 2023-03-01 10:43:53
 */
public interface UBindingService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UBinding queryById(Long id);

    /**
     * 分页查询
     *
     * @param uBinding    筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<UBinding> queryByPage(UBinding uBinding, PageRequest pageRequest);

    List<UBinding> queryAll(UBinding uBinding);

    List<UBinding> queryByTeacherId(Long teacherId);

    JSONObject doBinding(Long userId, Long teacherId);
    /**
     * 新增数据
     *
     * @param uBinding 实例对象
     * @return 实例对象
     */
    UBinding insert(UBinding uBinding);

    /**
     * 修改数据
     *
     * @param uBinding 实例对象
     * @return 实例对象
     */
    UBinding update(UBinding uBinding);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
