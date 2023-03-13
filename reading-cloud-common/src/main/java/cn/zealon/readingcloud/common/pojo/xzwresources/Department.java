package cn.zealon.readingcloud.common.pojo.xzwresources;

import cn.zealon.readingcloud.common.utils.LongJsonDeserializer;
import cn.zealon.readingcloud.common.utils.LongJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;
import java.io.Serializable;

/**
 * 部编同步作文(Department)实体类
 *
 * @author makejava
 * @since 2023-03-01 11:13:56
 */
public class Department implements Serializable {
    private static final long serialVersionUID = 836186426854394802L;
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
    private String dTitle;
    /**
     * 字数
     */
    private Integer dWords;
    /**
     * 内容
     */
    private String dContents;
    /**
     * 年级
     */
    private String dGrade;
    /**
     * 学期
     */
    private String dTerm;
    /**
     * 单元
     */
    private String dUnitarea;
    /**
     * 阅读次数
     */
    private Integer dReadtimes;
    /**
     * 图片
     */
    private String dImage;


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

    public String getDTitle() {
        return dTitle;
    }

    public void setDTitle(String dTitle) {
        this.dTitle = dTitle;
    }

    public Integer getDWords() {
        return dWords;
    }

    public void setDWords(Integer dWords) {
        this.dWords = dWords;
    }

    public String getDContents() {
        return dContents;
    }

    public void setDContents(String dContents) {
        this.dContents = dContents;
    }

    public String getDGrade() {
        return dGrade;
    }

    public void setDGrade(String dGrade) {
        this.dGrade = dGrade;
    }

    public String getDTerm() {
        return dTerm;
    }

    public void setDTerm(String dTerm) {
        this.dTerm = dTerm;
    }

    public String getDUnitarea() {
        return dUnitarea;
    }

    public void setDUnitarea(String dUnitarea) {
        this.dUnitarea = dUnitarea;
    }

    public Integer getDReadtimes() {
        return dReadtimes;
    }

    public void setDReadtimes(Integer dReadtimes) {
        this.dReadtimes = dReadtimes;
    }

    public String getDImage() {
        return dImage;
    }

    public void setDImage(String dImage) {
        this.dImage = dImage;
    }

}

