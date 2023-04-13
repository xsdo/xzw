package cn.zealon.readingcloud.book.vo;

public class StudentVO {
    /**
     * 学生姓名
     */
    private String studentName;
    /**
     * 状态（0未送达 1未完成 2已完成）
     */
    private Integer status;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
