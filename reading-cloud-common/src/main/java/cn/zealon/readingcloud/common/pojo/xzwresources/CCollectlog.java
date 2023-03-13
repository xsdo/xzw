package cn.zealon.readingcloud.common.pojo.xzwresources;

import java.util.Date;
import java.io.Serializable;

/**
 * 收藏夹文件表(CCollectlog)实体类
 *
 * @author makejava
 * @since 2023-03-10 17:51:36
 */
public class CCollectlog implements Serializable {
    private static final long serialVersionUID = -38771998331846013L;
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
     * 收藏夹id
     */
    private Long collectId;
    /**
     * 作文名
     */
    private String cName;
    /**
     * 作文图
     */
    private String cImage;
    /**
     * 作文ID
     */
    private Long compositionId;
    /**
     * 作文类型
     */
    private Integer cType;


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

    public Long getCollectId() {
        return collectId;
    }

    public void setCollectId(Long collectId) {
        this.collectId = collectId;
    }

    public String getCName() {
        return cName;
    }

    public void setCName(String cName) {
        this.cName = cName;
    }

    public String getCImage() {
        return cImage;
    }

    public void setCImage(String cImage) {
        this.cImage = cImage;
    }

    public Long getCompositionId() {
        return compositionId;
    }

    public void setCompositionId(Long compositionId) {
        this.compositionId = compositionId;
    }

    public Integer getCType() {
        return cType;
    }

    public void setCType(Integer cType) {
        this.cType = cType;
    }

}

