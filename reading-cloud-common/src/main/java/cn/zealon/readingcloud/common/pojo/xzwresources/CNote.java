package cn.zealon.readingcloud.common.pojo.xzwresources;

import cn.zealon.readingcloud.common.utils.LongJsonDeserializer;
import cn.zealon.readingcloud.common.utils.LongJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;
import java.io.Serializable;

/**
 * 笔记表(CNote)实体类
 *
 * @author makejava
 * @since 2023-03-08 14:27:21
 */
public class CNote implements Serializable {
    private static final long serialVersionUID = 299716849835045051L;
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
     * 用户ID
     */
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long userId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 用户头像
     */
    private String userHead;
    /**
     * 用户标签
     */
    private String userTable;
    /**
     * 内容
     */
    private String content;
    /**
     * 图片
     */
    private String images;
    /**
     * 音频id
     */
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long voice;
    /**
     * 视频id
     */
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long videos;
    /**
     * 评论数
     */
    private Integer discuss;
    /**
     * 点赞数
     */
    private Integer likes;
    /**
     * 阅读次数
     */
    private Integer readtimes;
    /**
     * 状态（已投稿：1；审核中：2；审核通过（发布）：3）
     */
    private Integer status;
    /**
     * 类型（默认随笔：0；文章：1；音频：2；视频：3）
     */
    private Integer type;
    /**
     * 引用文章id
     */
    private Long compositionId;


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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    public String getUserTable() {
        return userTable;
    }

    public void setUserTable(String userTable) {
        this.userTable = userTable;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public Long getVoice() {
        return voice;
    }

    public void setVoice(Long voice) {
        this.voice = voice;
    }

    public Long getVideos() {
        return videos;
    }

    public void setVideos(Long videos) {
        this.videos = videos;
    }

    public Integer getDiscuss() {
        return discuss;
    }

    public void setDiscuss(Integer discuss) {
        this.discuss = discuss;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getReadtimes() {
        return readtimes;
    }

    public void setReadtimes(Integer readtimes) {
        this.readtimes = readtimes;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getCompositionId() {
        return compositionId;
    }

    public void setCompositionId(Long compositionId) {
        this.compositionId = compositionId;
    }

}

