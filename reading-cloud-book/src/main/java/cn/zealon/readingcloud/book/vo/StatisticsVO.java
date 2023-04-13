package cn.zealon.readingcloud.book.vo;

import java.math.BigDecimal;
import java.util.List;

public class StatisticsVO {

    private int studentCount;

    private int taskCount;

    private int finishCount;

    private int unfinishCount;

    private int sendCount;

    private BigDecimal finishRate;

    private List<String> unfinishStudentNames;

    public int getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }

    public int getTaskCount() {
        return taskCount;
    }

    public void setTaskCount(int taskCount) {
        this.taskCount = taskCount;
    }

    public int getFinishCount() {
        return finishCount;
    }

    public void setFinishCount(int finishCount) {
        this.finishCount = finishCount;
    }

    public int getUnfinishCount() {
        return unfinishCount;
    }

    public void setUnfinishCount(int unfinishCount) {
        this.unfinishCount = unfinishCount;
    }

    public int getSendCount() {
        return sendCount;
    }

    public void setSendCount(int sendCount) {
        this.sendCount = sendCount;
    }

    public BigDecimal getFinishRate() {
        return finishRate;
    }

    public void setFinishRate(BigDecimal finishRate) {
        this.finishRate = finishRate;
    }

    public List<String> getUnfinishStudentNames() {
        return unfinishStudentNames;
    }
    public void setUnfinishStudentNames(List<String> unfinishStudentNames) {
        this.unfinishStudentNames = unfinishStudentNames;
    }
}
