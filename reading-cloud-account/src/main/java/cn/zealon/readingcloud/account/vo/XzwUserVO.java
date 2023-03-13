package cn.zealon.readingcloud.account.vo;

import lombok.Data;

@Data
public class XzwUserVO {
    private static final long serialVersionUID = -82495135615768873L;
    /**
     * ID
     */
    private Long id;
    /**
     * 是否使用（默认0：启用； 1：废弃）
     */
    private Integer isused;
    /**
     * 属性绑定id，查询用户属性
     */
    private Long uAttributeid;
    /**
     * 电话号码
     */
    private String phoneNumber;
    /**
     * 微信登录唯一标识
     */
    private String openId;

}
