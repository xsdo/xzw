package cn.zealon.readingcloud.common.pojo.xzwusers;

import cn.zealon.readingcloud.common.utils.LongJsonDeserializer;
import cn.zealon.readingcloud.common.utils.LongJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;
import java.io.Serializable;

/**
 * 用户标签表(UTable)实体类
 *
 * @author makejava
 * @since 2023-03-01 10:54:07
 */
public class UTable implements Serializable {
    private static final long serialVersionUID = 283409789104835547L;
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
     * 标签名称
     */
    private String uTableName;
    /**
     * 标签样式
     */
    private String uTablePattern;
    /**
     * 标签类型（默认：0；任务：1；活动：2）
     */
    private Integer uTableType;
    /**
     * 标签来源（url）
     */
    private String uTableSource;
    /**
     * 标签描述
     */
    private String uTableDescribe;


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

    public String getUTableName() {
        return uTableName;
    }

    public void setUTableName(String uTableName) {
        this.uTableName = uTableName;
    }

    public String getUTablePattern() {
        return uTablePattern;
    }

    public void setUTablePattern(String uTablePattern) {
        this.uTablePattern = uTablePattern;
    }

    public Integer getUTableType() {
        return uTableType;
    }

    public void setUTableType(Integer uTableType) {
        this.uTableType = uTableType;
    }

    public String getUTableSource() {
        return uTableSource;
    }

    public void setUTableSource(String uTableSource) {
        this.uTableSource = uTableSource;
    }

    public String getUTableDescribe() {
        return uTableDescribe;
    }

    public void setUTableDescribe(String uTableDescribe) {
        this.uTableDescribe = uTableDescribe;
    }

}

