package com.beans;

import java.util.Date;

public class Task {
	//attributes
	private int id;
	private String title;
	private String description;
	private Date dateAssigned;
	private Date dateStarted;
	private Date dateCompleted;
	private int userId;
	private int groupId;
	
	public Task(int id, String title, String description, Date dateAssigned,
			Date dateStarted, Date dateCompleted, int userId, int groupId) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.dateAssigned = dateAssigned;
		this.dateStarted = dateStarted;
		this.dateCompleted = dateCompleted;
		this.userId = userId;
		this.groupId = groupId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDateAssigned() {
		return dateAssigned;
	}

	public void setDateAssigned(Date dateAssigned) {
		this.dateAssigned = dateAssigned;
	}

	public Date getDateStarted() {
		return dateStarted;
	}

	public void setDateStarted(Date dateStarted) {
		this.dateStarted = dateStarted;
	}

	public Date getDateCompleted() {
		return dateCompleted;
	}

	public void setDateCompleted(Date dateCompleted) {
		this.dateCompleted = dateCompleted;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", title=" + title + ", description="
				+ description + ", dateAssigned=" + dateAssigned
				+ ", dateStarted=" + dateStarted + ", dateCompleted="
				+ dateCompleted + ", userId=" + userId + ", groupId=" + groupId
				+ "]";
	}
	
}
