package cn.zealon.readingcloud.common.pojo.xzwtasks;

import cn.zealon.readingcloud.common.utils.LongJsonDeserializer;
import cn.zealon.readingcloud.common.utils.LongJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;
import java.io.Serializable;

/**
 * 推荐作文表(SuggestTask)实体类
 *
 * @author makejava
 * @since 2023-03-01 11:11:43
 */
public class SuggestTask implements Serializable {
    private static final long serialVersionUID = -13306047625806828L;
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
     * 作文id
     */
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long compositionId;
    /**
     * 作文类型
     */
    private String compositionType;
    /**
     * 作文名
     */
    private String comName;
    /**
     * 作文图
     */
    private String comImage;
    /**
     * 点赞数
     */
    private Integer comLikes;
    /**
     * 评论数
     */
    private Integer comDiscuss;
    /**
     * 是否置顶
     */
    private Integer tTop;
    /**
     * 置顶结束时间
     */
    private Date tEndtime;


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

    public Long getCompositionId() {
        return compositionId;
    }

    public void setCompositionId(Long compositionId) {
        this.compositionId = compositionId;
    }

    public String getCompositionType() {
        return compositionType;
    }

    public void setCompositionType(String compositionType) {
        this.compositionType = compositionType;
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public String getComImage() {
        return comImage;
    }

    public void setComImage(String comImage) {
        this.comImage = comImage;
    }

    public Integer getComLikes() {
        return comLikes;
    }

    public void setComLikes(Integer comLikes) {
        this.comLikes = comLikes;
    }

    public Integer getComDiscuss() {
        return comDiscuss;
    }

    public void setComDiscuss(Integer comDiscuss) {
        this.comDiscuss = comDiscuss;
    }

    public Integer getTTop() {
        return tTop;
    }

    public void setTTop(Integer tTop) {
        this.tTop = tTop;
    }

    public Date getTEndtime() {
        return tEndtime;
    }

    public void setTEndtime(Date tEndtime) {
        this.tEndtime = tEndtime;
    }

}

