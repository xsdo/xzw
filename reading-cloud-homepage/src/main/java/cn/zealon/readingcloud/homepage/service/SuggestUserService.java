package cn.zealon.readingcloud.homepage.service;

import cn.zealon.readingcloud.common.pojo.xzwtasks.SuggestUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 推荐用户表(SuggestUser)表服务接口
 *
 * @author makejava
 * @since 2023-03-01 11:11:40
 */
public interface SuggestUserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SuggestUser queryById(Long id);

    /**
     * 分页查询
     *
     * @param suggestUser 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<SuggestUser> queryByPage(SuggestUser suggestUser, PageRequest pageRequest);
    List<SuggestUser> queryAll(SuggestUser suggestUser);

    /**
     * 新增数据
     *
     * @param suggestUser 实例对象
     * @return 实例对象
     */
    SuggestUser insert(SuggestUser suggestUser);

    /**
     * 修改数据
     *
     * @param suggestUser 实例对象
     * @return 实例对象
     */
    SuggestUser update(SuggestUser suggestUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
