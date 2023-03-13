package cn.zealon.readingcloud.common.pojo.xzwresources;

import cn.zealon.readingcloud.common.utils.LongJsonDeserializer;
import cn.zealon.readingcloud.common.utils.LongJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;
import java.io.Serializable;

/**
 * 校园作文表(CSchool)实体类
 *
 * @author makejava
 * @since 2023-03-01 11:13:57
 */
public class CSchool implements Serializable {
    private static final long serialVersionUID = -28249356781563182L;
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
     * 文章图片
     */
    private String sImage;
    /**
     * 文章音频id
     */
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long sVoice;
    /**
     * 点赞数
     */
    private Integer sLikes;
    /**
     * 评论数
     */
    private Integer sDiscuss;


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

    public String getSImage() {
        return sImage;
    }

    public void setSImage(String sImage) {
        this.sImage = sImage;
    }

    public Long getSVoice() {
        return sVoice;
    }

    public void setSVoice(Long sVoice) {
        this.sVoice = sVoice;
    }

    public Integer getSLikes() {
        return sLikes;
    }

    public void setSLikes(Integer sLikes) {
        this.sLikes = sLikes;
    }

    public Integer getSDiscuss() {
        return sDiscuss;
    }

    public void setSDiscuss(Integer sDiscuss) {
        this.sDiscuss = sDiscuss;
    }

}

