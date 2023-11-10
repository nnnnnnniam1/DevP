package com.devP.VO;


//VO(Value Object)
public class ReportVO {

    private String userId;
    private String userName;
    private String taskCount;
    private int taskPercentage;
    private int deadlineSuccessTaskCount;
    private String deadlineSuccessTaskCountPercentage;
    private int lateTaskCount;

    private String lateTaskPercentage;

    private int modifiedTaskCount;
    private String modifiedTaskPercentage;
    private int issueTaskCount;
    private String issueTaskPercentage;
    private int nonIssueLateTaskCount;
    private String nonIssueLateTaskDetail;

    private String reason;
    private String deleteDate;



    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTaskCount() {
        return taskCount;
    }
    public void setTaskCount(String taskCount) {
        this.taskCount = taskCount;
    }
    public int getTaskPercentage() {
        return taskPercentage;
    }
    public void setTaskPercentage(int taskPercentage) {
        this.taskPercentage = taskPercentage;
    }
    public int getDeadlineSuccessTaskCount() {
        return deadlineSuccessTaskCount;
    }
    public void setDeadlineSuccessTaskCount(int deadlineSuccessTaskCount) {
        this.deadlineSuccessTaskCount = deadlineSuccessTaskCount;
    }
    public String getDeadlineSuccessTaskCountPercentage() {
        return deadlineSuccessTaskCountPercentage;
    }
    public void setDeadlineSuccessTaskCountPercentage(String deadlineSuccessTaskCountPercentage) {
        this.deadlineSuccessTaskCountPercentage = deadlineSuccessTaskCountPercentage;
    }
    public int getLateTaskCount() {
        return lateTaskCount;
    }
    public void setLateTaskCount(int lateTaskCount) {
        this.lateTaskCount = lateTaskCount;
    }
    public String getLateTaskPercentage() {
        return lateTaskPercentage;
    }
    public void setLateTaskPercentage(String lateTaskPercentage) {
        this.lateTaskPercentage = lateTaskPercentage;
    }
    public int getModifiedTaskCount() {
        return modifiedTaskCount;
    }
    public void setModifiedTaskCount(int modifiedTaskCount) {
        this.modifiedTaskCount = modifiedTaskCount;
    }
    public String getModifiedTaskPercentage() {
        return modifiedTaskPercentage;
    }
    public void setModifiedTaskPercentage(String modifiedTaskPercentage) {
        this.modifiedTaskPercentage = modifiedTaskPercentage;
    }
    public int getIssueTaskCount() {
        return issueTaskCount;
    }
    public void setIssueTaskCount(int issueTaskCount) {
        this.issueTaskCount = issueTaskCount;
    }
    public String getIssueTaskPercentage() {
        return issueTaskPercentage;
    }
    public void setIssueTaskPercentage(String issueTaskPercentage) {
        this.issueTaskPercentage = issueTaskPercentage;
    }
    public int getNonIssueLateTaskCount() { return nonIssueLateTaskCount; }
    public void setNonIssueLateTaskCount(int nonIssueLateTaskCount) {
        this.nonIssueLateTaskCount = nonIssueLateTaskCount;
    }
    public String getNonIssueLateTaskDetail() {
        return nonIssueLateTaskDetail;
    }
    public void setNonIssueLateTaskDetail(String nonIssueLateTaskDetail) {
        this.nonIssueLateTaskDetail = nonIssueLateTaskDetail;
    }

    public String getReason(){return reason;}
    public void setReason(String reason){this.reason = reason;}

    public String getDeleteDate(){return deleteDate;}
    public void setDeleteDate(String deleteDate){this.deleteDate = deleteDate;}


}