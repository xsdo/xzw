package cn.zealon.readingcloud.common.pojo.xzwtasks;

import cn.zealon.readingcloud.common.utils.LongJsonDeserializer;
import cn.zealon.readingcloud.common.utils.LongJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;
import java.io.Serializable;

/**
 * 认证任务表(AuthTask)实体类
 *
 * @author makejava
 * @since 2023-03-01 11:11:41
 */
public class AuthTask implements Serializable {
    private static final long serialVersionUID = 446532081527549732L;
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
     * 任务名
     */
    private String aName;
    /**
     * 任务描述
     */
    private String aSynopsis;
    /**
     * 任务状态（默认：0开启；关闭：1）
     */
    private Integer aStatus;
    /**
     * 任务类型
     */
    private String aType;
    /**
     * 任务奖励
     */
    private String aAward;


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

    public String getAName() {
        return aName;
    }

    public void setAName(String aName) {
        this.aName = aName;
    }

    public String getASynopsis() {
        return aSynopsis;
    }

    public void setASynopsis(String aSynopsis) {
        this.aSynopsis = aSynopsis;
    }

    public Integer getAStatus() {
        return aStatus;
    }

    public void setAStatus(Integer aStatus) {
        this.aStatus = aStatus;
    }

    public String getAType() {
        return aType;
    }

    public void setAType(String aType) {
        this.aType = aType;
    }

    public String getAAward() {
        return aAward;
    }

    public void setAAward(String aAward) {
        this.aAward = aAward;
    }

}

