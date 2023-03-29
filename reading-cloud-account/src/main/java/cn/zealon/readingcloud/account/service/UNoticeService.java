package cn.zealon.readingcloud.account.service;

import cn.zealon.readingcloud.common.pojo.xzwusers.UNotice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 消息通知表(UNotice)表服务接口
 *
 * @author makejava
 * @since 2023-03-09 17:33:38
 */
public interface UNoticeService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UNotice queryById(Long id);

    /**
     * 分页查询
     *
     * @param uNotice     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<UNotice> queryByPage(UNotice uNotice, PageRequest pageRequest);

    List<UNotice> queryAll(UNotice uNotice);

    void doNotice(Long userId,String name,int type,String coment);

    long countNotice (Long userId);

    List<UNotice> readNotice(Long userId);
    /**
     * 新增数据
     *
     * @param uNotice 实例对象
     * @return 实例对象
     */
    UNotice insert(UNotice uNotice);

    /**
     * 修改数据
     *
     * @param uNotice 实例对象
     * @return 实例对象
     */
    UNotice update(UNotice uNotice);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
