package com.devP.VO;

import java.util.Date;

//VO(Value Object)
public class ProjectVO {
	private int projectId;
	private String projectName;
	private Date startDate;
	private Date endDate;
	private int wbsFileId;
	private int planFileId;	
	private int progress;	
	

	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public int getWbsFileId() {
		return wbsFileId;
	}
	public void setWbsFileId(int wbsFileId) {
		this.wbsFileId = wbsFileId;
	}
	public int getPlanFileId() {
		return planFileId;
	}
	public void setPlanFileId(int planFileId) {
		this.planFileId = planFileId;
	}
	public int getProgress() {
		return progress;
	}
	public void setProgress(int progress) {
		this.progress = progress;
	}
}