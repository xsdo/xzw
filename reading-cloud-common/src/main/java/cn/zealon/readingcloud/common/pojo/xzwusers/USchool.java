package cn.zealon.readingcloud.common.pojo.xzwusers;

import cn.zealon.readingcloud.common.utils.LongJsonDeserializer;
import cn.zealon.readingcloud.common.utils.LongJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;
import java.io.Serializable;

/**
 * 用户学校表(USchool)实体类
 *
 * @author makejava
 * @since 2023-03-06 09:23:22
 */
public class USchool implements Serializable {
    private static final long serialVersionUID = 378401876628145252L;
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
     * 学校名称
     */
    private String sName;
    /**
     * 学校省份
     */
    private String sProvince;
    /**
     * 学校所在市
     */
    private String sCity;
    /**
     * 学校所在区/县
     */
    private String sArea;
    /**
     * 学校总鲜花
     */
    private Integer sFlowers;


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

    public String getSName() {
        return sName;
    }

    public void setSName(String sName) {
        this.sName = sName;
    }

    public String getSProvince() {
        return sProvince;
    }

    public void setSProvince(String sProvince) {
        this.sProvince = sProvince;
    }

    public String getSCity() {
        return sCity;
    }

    public void setSCity(String sCity) {
        this.sCity = sCity;
    }

    public String getSArea() {
        return sArea;
    }

    public void setSArea(String sArea) {
        this.sArea = sArea;
    }

    public Integer getSFlowers() {
        return sFlowers;
    }

    public void setSFlowers(Integer sFlowers) {
        this.sFlowers = sFlowers;
    }

}

