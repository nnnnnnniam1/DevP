package com.devP.VO;

import java.util.Date;

//VO(Value Object)
public class ChatVO {
	private int chatId;
	private int projectId;
	private String from_id;
	private String to_id;
	private String sbject;
	private String time;
	public int getChatId() {
		return chatId;
	}
	public void setChatId(int chatId) {
		this.chatId = chatId;
	}
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public String getFrom_id() {
		return from_id;
	}
	public void setFrom_id(String from_id) {
		this.from_id = from_id;
	}
	public String getTo_id() {
		return to_id;
	}
	public void setTo_id(String to_id) {
		this.to_id = to_id;
	}
	public String getSbject() {
		return sbject;
	}
	public void setSbject(String sbject) {
		this.sbject = sbject;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	
}