package cn.zealon.readingcloud.common.pojo.xzwusers;

import cn.zealon.readingcloud.common.utils.LongJsonDeserializer;
import cn.zealon.readingcloud.common.utils.LongJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;
import java.io.Serializable;

/**
 * 用户属性表(UAttribute)实体类
 *
 * @author makejava
 * @since 2023-03-06 09:23:21
 */
public class UAttribute implements Serializable {
    private static final long serialVersionUID = 182159397474576469L;
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
     * 性别（默认：0；男：1；女：2）
     */
    private Integer sex;
    /**
     * 生日
     */
    private Date birthday;
    /**
     * 头像（url）
     */
    private String portrait;
    /**
     * qq
     */
    private String qqnum;
    /**
     * 微信号
     */
    private String wechatnum;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 用户类型(默认：0；读者：1；作者：2；编辑：3；播音：4；教师：5；代理商：6)
     */
    private Integer uType;
    /**
     * 展示标签id，绑定标签表
     */
    private Integer uTableuse;
    /**
     * 已获取标签id集合
     */
    private String uTableids;
    /**
     * 省
     */
    private String province;
    /**
     * 市
     */
    private String city;
    /**
     * 县、区
     */
    private String area;
    /**
     * 学校
     */
    private String school;
    /**
     * 是否认证用户(no0;yes1)
     */
    private Integer isAuth;
    /**
     * 个性签名
     */
    private String sign;
    /**
     * 背景图（url）
     */
    private String background;
    /**
     * 绑定老师id
     */
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long teacherid;
    /**
     * 是否优评员（默认0：非；优评：1）
     */
    private Integer isgoodcommons;
    /**
     * 积分
     */
    private Integer integral;


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

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getQqnum() {
        return qqnum;
    }

    public void setQqnum(String qqnum) {
        this.qqnum = qqnum;
    }

    public String getWechatnum() {
        return wechatnum;
    }

    public void setWechatnum(String wechatnum) {
        this.wechatnum = wechatnum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getUType() {
        return uType;
    }

    public void setUType(Integer uType) {
        this.uType = uType;
    }

    public Integer getUTableuse() {
        return uTableuse;
    }

    public void setUTableuse(Integer uTableuse) {
        this.uTableuse = uTableuse;
    }

    public String getUTableids() {
        return uTableids;
    }

    public void setUTableids(String uTableids) {
        this.uTableids = uTableids;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public Integer getIsAuth() {
        return isAuth;
    }

    public void setIsAuth(Integer isAuth) {
        this.isAuth = isAuth;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public Long getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(Long teacherid) {
        this.teacherid = teacherid;
    }

    public Integer getIsgoodcommons() {
        return isgoodcommons;
    }

    public void setIsgoodcommons(Integer isgoodcommons) {
        this.isgoodcommons = isgoodcommons;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

}

