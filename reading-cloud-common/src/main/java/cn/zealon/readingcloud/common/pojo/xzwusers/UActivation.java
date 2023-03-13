package cn.zealon.readingcloud.common.pojo.xzwusers;

import cn.zealon.readingcloud.common.utils.LongJsonDeserializer;
import cn.zealon.readingcloud.common.utils.LongJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;
import java.io.Serializable;

/**
 * 激活码表(UActivation)实体类
 *
 * @author makejava
 * @since 2023-03-01 10:54:07
 */
public class UActivation implements Serializable {
    private static final long serialVersionUID = 981179303550483250L;
    /**
     * ID
     */
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;
    /**
     * 是否使用（默认0：启用； 1：废弃）
     */
    private Integer isused;
    /**
     * 创建日期
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 会员类型（默认：0注册会员；终身会员：1；年卡：2）
     */
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long vipType;
    /**
     * 激活码创建时间
     */
    private Date vCreatetime;
    /**
     * 激活码到期时间
     */
    private Date vEndtime;
    /**
     * 激活码
     */
    private String activationCode;
    /**
     * 激活码状态（默认：0未激活；激活：1）
     */
    private Integer status;
    /**
     * 激活时间
     */
    private Date vUsetime;
    /**
     * 激活用户id
     */
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long userId;
    /**
     * 建码人
     */
    private String createUser;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIsused() {
        return isused;
    }

    public void setIsused(Integer isused) {
        this.isused = isused;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Long getVipType() {
        return vipType;
    }

    public void setVipType(Long vipType) {
        this.vipType = vipType;
    }

    public Date getVCreatetime() {
        return vCreatetime;
    }

    public void setVCreatetime(Date vCreatetime) {
        this.vCreatetime = vCreatetime;
    }

    public Date getVEndtime() {
        return vEndtime;
    }

    public void setVEndtime(Date vEndtime) {
        this.vEndtime = vEndtime;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getVUsetime() {
        return vUsetime;
    }

    public void setVUsetime(Date vUsetime) {
        this.vUsetime = vUsetime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

}

