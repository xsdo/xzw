package cn.zealon.readingcloud.common.pojo.xzwtasks;

import cn.zealon.readingcloud.common.utils.LongJsonDeserializer;
import cn.zealon.readingcloud.common.utils.LongJsonSerializer;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;
import java.io.Serializable;

/**
 * 阅读任务表(ReadTask)实体类
 *
 * @author makejava
 * @since 2023-03-01 11:11:42
 */
public class ReadTask implements Serializable {
    private static final long serialVersionUID = -72120948514449660L;
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
     * 任务标题
     */
    private String rTitle;
    /**
     * 任务描述
     */
    private String rSynopsis;
    /**
     * 任务起始时间
     */
    private Date rBegintime;
    /**
     * 任务结束时间
     */
    private Date rEndtime;
    /**
     * 任务状态（默认：0未开始；已开始：1；已结束：2）
     */
    private Integer rStatus;
    /**
     * 任务1url
     */
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long rTaskfirst;
    /**
     * 任务2url
     */
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long rTasksecond;
    /**
     * 需要阅读时间
     */
    private String rTime;


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

    public String getRTitle() {
        return rTitle;
    }

    public void setRTitle(String rTitle) {
        this.rTitle = rTitle;
    }

    public String getRSynopsis() {
        return rSynopsis;
    }

    public void setRSynopsis(String rSynopsis) {
        this.rSynopsis = rSynopsis;
    }

    public Date getRBegintime() {
        return rBegintime;
    }

    public void setRBegintime(Date rBegintime) {
        this.rBegintime = rBegintime;
    }

    public Date getREndtime() {
        return rEndtime;
    }

    public void setREndtime(Date rEndtime) {
        this.rEndtime = rEndtime;
    }

    public Integer getRStatus() {
        return rStatus;
    }

    public void setRStatus(Integer rStatus) {
        this.rStatus = rStatus;
    }

    public Long getRTaskfirst() {
        return rTaskfirst;
    }

    public void setRTaskfirst(Long rTaskfirst) {
        this.rTaskfirst = rTaskfirst;
    }

    public Long getRTasksecond() {
        return rTasksecond;
    }

    public void setRTasksecond(Long rTasksecond) {
        this.rTasksecond = rTasksecond;
    }

    public String getRTime() {
        return rTime;
    }

    public void setRTime(String rTime) {
        this.rTime = rTime;
    }

}

