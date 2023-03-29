package cn.zealon.readingcloud.account.service;

import cn.zealon.readingcloud.common.pojo.xzwusers.XzwUser;
import cn.zealon.readingcloud.common.result.Result;
import com.alibaba.fastjson.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * 用户表(XzwUser)表服务接口
 *
 * @author makejava
 * @since 2023-03-01 10:43:57
 */
public interface XzwUserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    XzwUser queryById(Long id);


    XzwUser queryByPhoneNumber(String phoneNumber);

    Result send4Login(String phoneNumber);

    Result loginByPhoneNumber(String phoneNumber, String validateCode);

    Result bindingPhoneNumber(Long userId,String phoneNumber,String validateCode);


    XzwUser queryByOpenId(String openId);

    String getOpeId(@RequestBody JSONObject js_code);

    Result loginByOpenId(String openId ,String nickName, String avatarUrl  );


    Result login(String phoneNumber);

    /**
     * 修改头像
     * @param file 文件
     * @return /
     */
//    Map<String, String> updateAvatar(MultipartFile file,XzwUser user);


    /**
     * 分页查询
     *
     * @param xzwUser     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<XzwUser> queryByPage(XzwUser xzwUser, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param xzwUser 实例对象
     * @return 实例对象
     */
    XzwUser insert(XzwUser xzwUser);

    /**
     * 修改数据
     *
     * @param xzwUser 实例对象
     * @return 实例对象
     */
    XzwUser update(XzwUser xzwUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
