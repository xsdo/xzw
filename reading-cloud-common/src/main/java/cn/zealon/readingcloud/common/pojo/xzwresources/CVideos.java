package cn.zealon.readingcloud.common.pojo.xzwresources;

import cn.zealon.readingcloud.common.utils.LongJsonDeserializer;
import cn.zealon.readingcloud.common.utils.LongJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;
import java.io.Serializable;

/**
 * 视频表(CVideos)实体类
 *
 * @author makejava
 * @since 2023-03-01 11:13:54
 */
public class CVideos implements Serializable {
    private static final long serialVersionUID = -91877458528596218L;
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
     * 视频名
     */
    private String vName;
    /**
     * 视频作者
     */
    private String vTeacher;
    /**
     * 视频
     */
    private String vVideos;
    /**
     * 视频类型（默认0：通用 ；专题：1 ；专辑：2； 同步：3； 宣传片：4）
     */
    private Integer vType;
    /**
     * 作者头衔
     */
    private String vRank;
    /**
     * 视频描述
     */
    private String vDescription;
    /**
     * 视频时间
     */
    private String vTime;
    /**
     * 年级
     */
    private String vGrade;
    /**
     * 学期
     */
    private String vTerm;
    /**
     * 单元
     */
    private String vUnitarea;
    /**
     * 图片
     */
    private String vImage;


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

    public String getVName() {
        return vName;
    }

    public void setVName(String vName) {
        this.vName = vName;
    }

    public String getVTeacher() {
        return vTeacher;
    }

    public void setVTeacher(String vTeacher) {
        this.vTeacher = vTeacher;
    }

    public String getVVideos() {
        return vVideos;
    }

    public void setVVideos(String vVideos) {
        this.vVideos = vVideos;
    }

    public Integer getVType() {
        return vType;
    }

    public void setVType(Integer vType) {
        this.vType = vType;
    }

    public String getVRank() {
        return vRank;
    }

    public void setVRank(String vRank) {
        this.vRank = vRank;
    }

    public String getVDescription() {
        return vDescription;
    }

    public void setVDescription(String vDescription) {
        this.vDescription = vDescription;
    }

    public String getVTime() {
        return vTime;
    }

    public void setVTime(String vTime) {
        this.vTime = vTime;
    }

    public String getVGrade() {
        return vGrade;
    }

    public void setVGrade(String vGrade) {
        this.vGrade = vGrade;
    }

    public String getVTerm() {
        return vTerm;
    }

    public void setVTerm(String vTerm) {
        this.vTerm = vTerm;
    }

    public String getVUnitarea() {
        return vUnitarea;
    }

    public void setVUnitarea(String vUnitarea) {
        this.vUnitarea = vUnitarea;
    }

    public String getVImage() {
        return vImage;
    }

    public void setVImage(String vImage) {
        this.vImage = vImage;
    }

}

