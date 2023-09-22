package com.devP.VO;

public class ProjectListVO {
    private String projectId;
    private String projectName;
    private String position;
    private String role;
    private String projectProgress;
    private String memberProgress;

    public String getMemberProgress() {
        return memberProgress;
    }

    public void setMemberProgress(String memberProgress) {
        this.memberProgress = memberProgress;
    }

    public String getProjectProgress() {
        return projectProgress;
    }

    public void setProjectProgress(String projectProgress) {
        this.projectProgress = projectProgress;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
