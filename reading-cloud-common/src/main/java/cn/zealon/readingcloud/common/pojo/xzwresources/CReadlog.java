package cn.zealon.readingcloud.common.pojo.xzwresources;

import java.util.Date;
import java.io.Serializable;

/**
 * 阅读记录表(CReadlog)实体类
 *
 * @author makejava
 * @since 2023-03-10 17:51:34
 */
public class CReadlog implements Serializable {
    private static final long serialVersionUID = -76184810088584209L;
    /**
     * ID
     */
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
    private Long userId;
    /**
     * 图片
     */
    private String rImage;
    /**
     * 文章名
     */
    private String rName;
    /**
     * 作文id
     */
    private Long compositionId;
    /**
     * 文章类型
     */
    private Integer rType;


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

    public String getRImage() {
        return rImage;
    }

    public void setRImage(String rImage) {
        this.rImage = rImage;
    }

    public String getRName() {
        return rName;
    }

    public void setRName(String rName) {
        this.rName = rName;
    }

    public Long getCompositionId() {
        return compositionId;
    }

    public void setCompositionId(Long compositionId) {
        this.compositionId = compositionId;
    }

    public Integer getRType() {
        return rType;
    }

    public void setRType(Integer rType) {
        this.rType = rType;
    }

}

