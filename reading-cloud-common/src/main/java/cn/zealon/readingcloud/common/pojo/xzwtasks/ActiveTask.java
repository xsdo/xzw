package cn.zealon.readingcloud.common.pojo.xzwtasks;

import cn.zealon.readingcloud.common.utils.LongJsonDeserializer;
import cn.zealon.readingcloud.common.utils.LongJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;
import java.io.Serializable;

/**
 * 活动任务表(ActiveTask)实体类
 *
 * @author makejava
 * @since 2023-03-01 11:11:40
 */
public class ActiveTask implements Serializable {
    private static final long serialVersionUID = -34498822926816637L;
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
     * 活动名
     */
    private String acName;
    /**
     * 活动简介
     */
    private String acSynopsis;
    /**
     * 活动状态（默认：0未开始；已开始：1；已结束：2）
     */
    private Integer acStatus;
    /**
     * 活动类型（默认：0活动；征文：1；推荐阅读：2）
     */
    private Integer acType;
    /**
     * 活动奖励
     */
    private String acAward;
    /**
     * 活动图
     */
    private String acImage;
    /**
     * 活动开启时间
     */
    private Date acBegintime;
    /**
     * 活动结束时间
     */
    private Date acEndtime;
    /**
     * 是否付费
     */
    private Integer acIspay;
    /**
     * 活动文案
     */
    private String acPaperwork;


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

    public String getAcName() {
        return acName;
    }

    public void setAcName(String acName) {
        this.acName = acName;
    }

    public String getAcSynopsis() {
        return acSynopsis;
    }

    public void setAcSynopsis(String acSynopsis) {
        this.acSynopsis = acSynopsis;
    }

    public Integer getAcStatus() {
        return acStatus;
    }

    public void setAcStatus(Integer acStatus) {
        this.acStatus = acStatus;
    }

    public Integer getAcType() {
        return acType;
    }

    public void setAcType(Integer acType) {
        this.acType = acType;
    }

    public String getAcAward() {
        return acAward;
    }

    public void setAcAward(String acAward) {
        this.acAward = acAward;
    }

    public String getAcImage() {
        return acImage;
    }

    public void setAcImage(String acImage) {
        this.acImage = acImage;
    }

    public Date getAcBegintime() {
        return acBegintime;
    }

    public void setAcBegintime(Date acBegintime) {
        this.acBegintime = acBegintime;
    }

    public Date getAcEndtime() {
        return acEndtime;
    }

    public void setAcEndtime(Date acEndtime) {
        this.acEndtime = acEndtime;
    }

    public Integer getAcIspay() {
        return acIspay;
    }

    public void setAcIspay(Integer acIspay) {
        this.acIspay = acIspay;
    }

    public String getAcPaperwork() {
        return acPaperwork;
    }

    public void setAcPaperwork(String acPaperwork) {
        this.acPaperwork = acPaperwork;
    }

}

