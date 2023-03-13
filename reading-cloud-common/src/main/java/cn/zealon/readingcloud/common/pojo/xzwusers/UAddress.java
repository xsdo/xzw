package cn.zealon.readingcloud.common.pojo.xzwusers;

import java.util.Date;
import java.io.Serializable;

/**
 * 用户地址表(UAddress)实体类
 *
 * @author makejava
 * @since 2023-03-10 17:54:34
 */
public class UAddress implements Serializable {
    private static final long serialVersionUID = -74142200987086275L;
    /**
     * ID
     */
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

    private Long userId;
    /**
     * 姓名
     */
    private String aName;
    /**
     * 电话
     */
    private String aPhonenumber;
    /**
     * 地址
     */
    private String aAddress;


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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAName() {
        return aName;
    }

    public void setAName(String aName) {
        this.aName = aName;
    }

    public String getAPhonenumber() {
        return aPhonenumber;
    }

    public void setAPhonenumber(String aPhonenumber) {
        this.aPhonenumber = aPhonenumber;
    }

    public String getAAddress() {
        return aAddress;
    }

    public void setAAddress(String aAddress) {
        this.aAddress = aAddress;
    }

}

