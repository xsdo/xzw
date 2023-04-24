package cn.zealon.readingcloud.account.service;

import cn.zealon.readingcloud.common.pojo.xzwusers.WebLogin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 扫码登录表(WebLogin)表服务接口
 *
 * @author makejava
 * @since 2023-04-18 13:38:38
 */
public interface WebLoginService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    WebLogin queryById(Long id);

    /**
     * 分页查询
     *
     * @param webLogin    筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<WebLogin> queryByPage(WebLogin webLogin, PageRequest pageRequest);

    WebLogin queryByUuid(String uuid);

    WebLogin queryQrCodeStatus(String uuid);

    void changeLoginStatus(String uuid);

    String loginWeb(String uuid,Long userId);
    /**
     * 新增数据
     *
     * @param webLogin 实例对象
     * @return 实例对象
     */
    WebLogin insert(WebLogin webLogin);

    /**
     * 修改数据
     *
     * @param webLogin 实例对象
     * @return 实例对象
     */
    WebLogin update(WebLogin webLogin);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
