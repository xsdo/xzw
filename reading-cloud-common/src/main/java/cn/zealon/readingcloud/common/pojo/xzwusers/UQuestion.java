package cn.zealon.readingcloud.common.pojo.xzwusers;

import cn.zealon.readingcloud.common.utils.LongJsonDeserializer;
import cn.zealon.readingcloud.common.utils.LongJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;
import java.io.Serializable;

/**
 * 问题反馈表(UQuestion)实体类
 *
 * @author makejava
 * @since 2023-03-10 17:54:35
 */
public class UQuestion implements Serializable {
    private static final long serialVersionUID = -19392874185376336L;
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

    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long userId;
    /**
     * 问题类型
     */
    private Integer qType;
    /**
     * 问题描述
     */
    private String qContent;
    /**
     * 邮箱
     */
    private String email;


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

    public Integer getQType() {
        return qType;
    }

    public void setQType(Integer qType) {
        this.qType = qType;
    }

    public String getQContent() {
        return qContent;
    }

    public void setQContent(String qContent) {
        this.qContent = qContent;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}

