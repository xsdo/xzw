package cn.zealon.readingcloud.common.pojo.xzwusers;

import java.util.Date;
import java.io.Serializable;

/**
 * 教师班级表(UTeacher)实体类
 *
 * @author makejava
 * @since 2023-03-10 09:57:25
 */
public class UTeacher implements Serializable {
    private static final long serialVersionUID = 748557903631574599L;
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
     * 名称
     */
    private String tName;
    /**
     * 绑定学校id
     */
    private Long tSchoolid;
    /**
     * 年级
     */
    private String tGrade;
    /**
     * 学期
     */
    private String tTerm;
    /**
     * 二维码
     */
    private String qrCode;
    /**
     * 学生人数
     */
    private Integer tStudent;
    /**
     * 班级鲜花数
     */
    private Integer tFlowers;
    /**
     * 班级头像
     */
    private String tImage;
    /**
     * 教师姓名
     */
    private String teaName;
    /**
     * 教师头像
     */
    private String teaImage;
    /**
     * 教师签名
     */
    private String teaSign;
    /**
     * 班级口号
     */
    private String tSlogan;
    /**
     * 写作数
     */
    private Integer writes;
    /**
     * 阅读数
     */
    private Integer readings;
    /**
     * 朗读数
     */
    private Integer readalouds;
    /**
     * 班级开关 0不可加入
     */
    private Integer tOpen;
    /**
     * 排名
     */
    private Integer rankings;


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

    public String getTName() {
        return tName;
    }

    public void setTName(String tName) {
        this.tName = tName;
    }

    public Long getTSchoolid() {
        return tSchoolid;
    }

    public void setTSchoolid(Long tSchoolid) {
        this.tSchoolid = tSchoolid;
    }

    public String getTGrade() {
        return tGrade;
    }

    public void setTGrade(String tGrade) {
        this.tGrade = tGrade;
    }

    public String getTTerm() {
        return tTerm;
    }

    public void setTTerm(String tTerm) {
        this.tTerm = tTerm;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public Integer getTStudent() {
        return tStudent;
    }

    public void setTStudent(Integer tStudent) {
        this.tStudent = tStudent;
    }

    public Integer getTFlowers() {
        return tFlowers;
    }

    public void setTFlowers(Integer tFlowers) {
        this.tFlowers = tFlowers;
    }

    public String getTImage() {
        return tImage;
    }

    public void setTImage(String tImage) {
        this.tImage = tImage;
    }

    public String getTeaName() {
        return teaName;
    }

    public void setTeaName(String teaName) {
        this.teaName = teaName;
    }

    public String getTeaImage() {
        return teaImage;
    }

    public void setTeaImage(String teaImage) {
        this.teaImage = teaImage;
    }

    public String getTeaSign() {
        return teaSign;
    }

    public void setTeaSign(String teaSign) {
        this.teaSign = teaSign;
    }

    public String getTSlogan() {
        return tSlogan;
    }

    public void setTSlogan(String tSlogan) {
        this.tSlogan = tSlogan;
    }

    public Integer getWrites() {
        return writes;
    }

    public void setWrites(Integer writes) {
        this.writes = writes;
    }

    public Integer getReadings() {
        return readings;
    }

    public void setReadings(Integer readings) {
        this.readings = readings;
    }

    public Integer getReadalouds() {
        return readalouds;
    }

    public void setReadalouds(Integer readalouds) {
        this.readalouds = readalouds;
    }

    public Integer getTOpen() {
        return tOpen;
    }

    public void setTOpen(Integer tOpen) {
        this.tOpen = tOpen;
    }

    public Integer getRankings() {
        return rankings;
    }

    public void setRankings(Integer rankings) {
        this.rankings = rankings;
    }

}

