package cn.zealon.readingcloud.common.pojo.xzwresources;

import cn.zealon.readingcloud.common.utils.LongJsonDeserializer;
import cn.zealon.readingcloud.common.utils.LongJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;
import java.io.Serializable;

/**
 * 杂志文章表(MArticles)实体类
 *
 * @author makejava
 * @since 2023-03-01 11:13:59
 */
public class MArticles implements Serializable {
    private static final long serialVersionUID = -34249087289422490L;
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
     * 标题id
     */
    private String mTitleid;
    /**
     * 文章内容
     */
    private String mContent;
    /**
     * 图片
     */
    private String mImage;
    /**
     * 是否试读(0否：1是）
     */
    private Integer mTry;
    /**
     * 音频
     */
    private String mVoice;
    /**
     * 视频
     */
    private String mVideos;


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

    public String getMTitleid() {
        return mTitleid;
    }

    public void setMTitleid(String mTitleid) {
        this.mTitleid = mTitleid;
    }

    public String getMContent() {
        return mContent;
    }

    public void setMContent(String mContent) {
        this.mContent = mContent;
    }

    public String getMImage() {
        return mImage;
    }

    public void setMImage(String mImage) {
        this.mImage = mImage;
    }

    public Integer getMTry() {
        return mTry;
    }

    public void setMTry(Integer mTry) {
        this.mTry = mTry;
    }

    public String getMVoice() {
        return mVoice;
    }

    public void setMVoice(String mVoice) {
        this.mVoice = mVoice;
    }

    public String getMVideos() {
        return mVideos;
    }

    public void setMVideos(String mVideos) {
        this.mVideos = mVideos;
    }

}

