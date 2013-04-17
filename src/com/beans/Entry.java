package com.beans;

import java.util.Date;

public class Entry {
	//attributes
	private int id;
	private Date dateTime;
	private String subject;
	private String message;
	private int parentId;
	private int userId;
	
	public Entry(int id, Date dateTime, String subject, String message, int parent_id, int user_id) {
		this.id = id;
		this.dateTime = dateTime;
		this.subject = subject;
		this.message = message;
		this.parentId = parent_id;
		this.userId = user_id;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Entry [id=" + id + ", dateTime=" + dateTime + ", subject="
				+ subject + ", message=" + message + ", parent_id=" + parentId
				+ ", user_id=" + userId + "]";
	}	
	
}
