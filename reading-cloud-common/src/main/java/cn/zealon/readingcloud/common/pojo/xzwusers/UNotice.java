package cn.zealon.readingcloud.common.pojo.xzwusers;

import cn.zealon.readingcloud.common.utils.LongJsonDeserializer;
import cn.zealon.readingcloud.common.utils.LongJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;
import java.io.Serializable;

/**
 * 消息通知表(UNotice)实体类
 *
 * @author makejava
 * @since 2023-03-10 09:06:42
 */
public class UNotice implements Serializable {
    private static final long serialVersionUID = -11256126284514996L;
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
     * 通知名
     */
    private String nName;
    /**
     * 消息类型:announcement公告/remind提醒/message私信
     */
    private Integer nType;
    /**
     * 是否已读,0未读,1已读
     */
    private Integer readoff;
    /**
     * 通知内容
     */
    private String coment;


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

    public String getNName() {
        return nName;
    }

    public void setNName(String nName) {
        this.nName = nName;
    }

    public Integer getNType() {
        return nType;
    }

    public void setNType(Integer nType) {
        this.nType = nType;
    }

    public Integer getReadoff() {
        return readoff;
    }

    public void setReadoff(Integer readoff) {
        this.readoff = readoff;
    }

    public String getComent() {
        return coment;
    }

    public void setComent(String coment) {
        this.coment = coment;
    }

}

