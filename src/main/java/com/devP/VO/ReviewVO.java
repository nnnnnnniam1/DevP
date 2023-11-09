package com.devP.VO;

public class ReviewVO {
    private int reviewId;
    private int projectId;
    private String writeMemberId;
    private String evaMemberId;
    private String evaluation;

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public String getEvaMemberId() {
        return evaMemberId;
    }

    public void setEvaMemberId(String evaMemberId) {
        this.evaMemberId = evaMemberId;
    }

    public String getWriteMemberId() {
        return writeMemberId;
    }

    public void setWriteMemberId(String userId) {
        this.writeMemberId = userId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }
}
