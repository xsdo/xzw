package cn.zealon.readingcloud.common.pojo.xzwresources;

import java.util.Date;
import java.io.Serializable;

/**
 * 投稿表(CSubmit)实体类
 *
 * @author makejava
 * @since 2023-03-10 17:51:35
 */
public class CSubmit implements Serializable {
    private static final long serialVersionUID = 987380720101962669L;
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

    private Long userId;
    /**
     * 投稿标题
     */
    private String sName;
    /**
     * 投稿内容
     */
    private String sContent;


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

    public String getSName() {
        return sName;
    }

    public void setSName(String sName) {
        this.sName = sName;
    }

    public String getSContent() {
        return sContent;
    }

    public void setSContent(String sContent) {
        this.sContent = sContent;
    }

}

