package com.devP.VO;

public class MemberVO {

    public int projectId;
    public String memberName;
    public String memberId;

    public String email;
    public String position;
    public String role;
    private String status;
    private float progress;

    public int getProjectId() {return projectId;}
    public void setProjectId(int projectId){this.projectId = projectId;}
    public String getMemberName() {return memberName;}
    public String getMemberId() {return memberId;}
    public void setMemberId(String memberId) {this.memberId = memberId;}
    public void setMemberName(String memberName) {this.memberName = memberName;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public String getPosition() {return position;}
    public void setPosition(String position) {this.position = position;}
    public String getRole() {return role;}
    public void setRole(String role) {this.role = role;}

    public String getStatus(){return status;}
    public void setStatus(String status){this.status = status;}

    public float getProgress(){return progress;}
    public void setProgress(float progress){this.progress = progress;}
}
