package cn.zealon.readingcloud.common.pojo.xzwtasks;

import cn.zealon.readingcloud.common.utils.LongJsonDeserializer;
import cn.zealon.readingcloud.common.utils.LongJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;
import java.io.Serializable;

/**
 * 轮播图(RotationTask)实体类
 *
 * @author makejava
 * @since 2023-03-01 11:11:41
 */
public class RotationTask implements Serializable {
    private static final long serialVersionUID = 571242388884420249L;
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
     * 轮播标题
     */
    private String rTitle;
    /**
     * 轮播图
     */
    private String rImage;
    /**
     * 轮播类型（默认：0；首页轮播：1；广告轮播：2；图书轮播：3；插播广告：4-唯一）
     */
    private Integer rType;
    /**
     * 跳转页链接
     */
    private String rUrl;
    /**
     * 结束时间
     */
    private Date rEndtime;


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

    public String getRImage() {
        return rImage;
    }

    public void setRImage(String rImage) {
        this.rImage = rImage;
    }

    public Integer getRType() {
        return rType;
    }

    public void setRType(Integer rType) {
        this.rType = rType;
    }

    public String getRUrl() {
        return rUrl;
    }

    public void setRUrl(String rUrl) {
        this.rUrl = rUrl;
    }

    public Date getREndtime() {
        return rEndtime;
    }

    public void setREndtime(Date rEndtime) {
        this.rEndtime = rEndtime;
    }

}

