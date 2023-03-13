package cn.zealon.readingcloud.account.service;

import cn.zealon.readingcloud.common.pojo.xzwusers.USigndetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 签到明细表(USigndetail)表服务接口
 *
 * @author makejava
 * @since 2023-03-07 09:20:54
 */
public interface USigndetailService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    USigndetail queryById(Long id);

    /**
     * 分页查询
     *
     * @param uSigndetail 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<USigndetail> queryByPage(USigndetail uSigndetail, PageRequest pageRequest);


    List<USigndetail> queryAllByUserId(Long userId);
    /**
     * 新增数据
     *
     * @param uSigndetail 实例对象
     * @return 实例对象
     */
    USigndetail insert(USigndetail uSigndetail);

    /**
     * 修改数据
     *
     * @param uSigndetail 实例对象
     * @return 实例对象
     */
    USigndetail update(USigndetail uSigndetail);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
