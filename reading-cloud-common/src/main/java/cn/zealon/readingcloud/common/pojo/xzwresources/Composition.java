package cn.zealon.readingcloud.common.pojo.xzwresources;

import cn.zealon.readingcloud.common.utils.LongJsonDeserializer;
import cn.zealon.readingcloud.common.utils.LongJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;
import java.io.Serializable;

/**
 * 作文表(Composition)实体类
 *
 * @author makejava
 * @since 2023-03-01 11:14:01
 */
public class Composition implements Serializable {
    private static final long serialVersionUID = -62853091606266557L;
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
     * 标题
     */
    private String cTitle;
    /**
     * 作者id
     */
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long cAuthorid;
    /**
     * 简介/提要
     */
    private String cSynopsis;
    /**
     * 内容
     */
    private String cArticle;
    /**
     * 图片大尺寸
     */
    private String cImageb;
    /**
     * 图片小尺寸
     */
    private String cImages;
    /**
     * 音频id
     */
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long cVoice;
    /**
     * 视频id
     */
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long cVideos;
    /**
     * 会员作文（默认：0非会员；会员作文：1）
     */
    private Integer cVip;
    /**
     * 级别（小低；小高）
     */
    private String cGrade;
    /**
     * 类型（记事；想象；写人；写事；议论等）
     */
    private String cGatetype;
    /**
     * 字数
     */
    private Integer cWords;
    /**
     * 状态（已投稿：1；审核中：2；审核通过（发布）：3）
     */
    private Integer cStatus;
    /**
     * 点赞数
     */
    private Integer cLikes;
    /**
     * 评论数
     */
    private Integer cDiscuss;
    /**
     * 阅读次数
     */
    private Integer cReadtimes;


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

    public String getCTitle() {
        return cTitle;
    }

    public void setCTitle(String cTitle) {
        this.cTitle = cTitle;
    }

    public Long getCAuthorid() {
        return cAuthorid;
    }

    public void setCAuthorid(Long cAuthorid) {
        this.cAuthorid = cAuthorid;
    }

    public String getCSynopsis() {
        return cSynopsis;
    }

    public void setCSynopsis(String cSynopsis) {
        this.cSynopsis = cSynopsis;
    }

    public String getCArticle() {
        return cArticle;
    }

    public void setCArticle(String cArticle) {
        this.cArticle = cArticle;
    }

    public String getCImageb() {
        return cImageb;
    }

    public void setCImageb(String cImageb) {
        this.cImageb = cImageb;
    }

    public String getCImages() {
        return cImages;
    }

    public void setCImages(String cImages) {
        this.cImages = cImages;
    }

    public Long getCVoice() {
        return cVoice;
    }

    public void setCVoice(Long cVoice) {
        this.cVoice = cVoice;
    }

    public Long getCVideos() {
        return cVideos;
    }

    public void setCVideos(Long cVideos) {
        this.cVideos = cVideos;
    }

    public Integer getCVip() {
        return cVip;
    }

    public void setCVip(Integer cVip) {
        this.cVip = cVip;
    }

    public String getCGrade() {
        return cGrade;
    }

    public void setCGrade(String cGrade) {
        this.cGrade = cGrade;
    }

    public String getCGatetype() {
        return cGatetype;
    }

    public void setCGatetype(String cGatetype) {
        this.cGatetype = cGatetype;
    }

    public Integer getCWords() {
        return cWords;
    }

    public void setCWords(Integer cWords) {
        this.cWords = cWords;
    }

    public Integer getCStatus() {
        return cStatus;
    }

    public void setCStatus(Integer cStatus) {
        this.cStatus = cStatus;
    }

    public Integer getCLikes() {
        return cLikes;
    }

    public void setCLikes(Integer cLikes) {
        this.cLikes = cLikes;
    }

    public Integer getCDiscuss() {
        return cDiscuss;
    }

    public void setCDiscuss(Integer cDiscuss) {
        this.cDiscuss = cDiscuss;
    }

    public Integer getCReadtimes() {
        return cReadtimes;
    }

    public void setCReadtimes(Integer cReadtimes) {
        this.cReadtimes = cReadtimes;
    }

}

