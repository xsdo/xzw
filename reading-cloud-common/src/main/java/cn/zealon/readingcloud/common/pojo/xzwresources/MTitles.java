package cn.zealon.readingcloud.common.pojo.xzwresources;

import cn.zealon.readingcloud.common.utils.LongJsonDeserializer;
import cn.zealon.readingcloud.common.utils.LongJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;
import java.io.Serializable;

/**
 * 杂志目录表(MTitles)实体类
 *
 * @author makejava
 * @since 2023-03-01 11:13:53
 */
public class MTitles implements Serializable {
    private static final long serialVersionUID = -38759596996232052L;
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
     * 文章标题
     */
    private String mTitle;
    /**
     * 栏目id
     */
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long pid;
    /**
     * 作者
     */
    private String mAuthor;
    /**
     * 学校
     */
    private String mSchool;
    /**
     * 是否试读(0否：1是）
     */
    private Integer mTry;
    /**
     * 图片
     */
    private String mImage;


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

    public String getMTitle() {
        return mTitle;
    }

    public void setMTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getMAuthor() {
        return mAuthor;
    }

    public void setMAuthor(String mAuthor) {
        this.mAuthor = mAuthor;
    }

    public String getMSchool() {
        return mSchool;
    }

    public void setMSchool(String mSchool) {
        this.mSchool = mSchool;
    }

    public Integer getMTry() {
        return mTry;
    }

    public void setMTry(Integer mTry) {
        this.mTry = mTry;
    }

    public String getMImage() {
        return mImage;
    }

    public void setMImage(String mImage) {
        this.mImage = mImage;
    }

}

