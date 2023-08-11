package com.devP.VO;

public class MemberVO {

    public int projectId;
    public String memberName;
    public String memberId;

    public String email;
    public String position;
    public String role;

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
}
