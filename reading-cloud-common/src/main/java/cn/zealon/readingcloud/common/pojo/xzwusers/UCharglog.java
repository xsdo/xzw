package cn.zealon.readingcloud.common.pojo.xzwusers;

import java.util.Date;
import java.io.Serializable;

/**
 * 充值记录表(UCharglog)实体类
 *
 * @author makejava
 * @since 2023-03-10 17:54:34
 */
public class UCharglog implements Serializable {
    private static final long serialVersionUID = -53075422868849211L;
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
     * 价格
     */
    private Integer cPrice;
    /**
     * 类型
     */
    private Integer cType;
    /**
     * 内容
     */
    private String cContent;
    /**
     * 付款类型
     */
    private Integer paytype;


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

    public Integer getCPrice() {
        return cPrice;
    }

    public void setCPrice(Integer cPrice) {
        this.cPrice = cPrice;
    }

    public Integer getCType() {
        return cType;
    }

    public void setCType(Integer cType) {
        this.cType = cType;
    }

    public String getCContent() {
        return cContent;
    }

    public void setCContent(String cContent) {
        this.cContent = cContent;
    }

    public Integer getPaytype() {
        return paytype;
    }

    public void setPaytype(Integer paytype) {
        this.paytype = paytype;
    }

}

