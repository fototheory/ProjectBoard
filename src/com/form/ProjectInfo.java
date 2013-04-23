package com.form;

import java.util.Date;

public class ProjectInfo {
	private int projectId;
	private String title;
	private String desc;
	private Date due;
	private String action;
	
	public ProjectInfo() {
		
	}
	public ProjectInfo(int projectId, String title, String desc, Date due,
			String action) {
		super();
		this.projectId = projectId;
		this.title = title;
		this.desc = desc;
		this.due = due;
		this.action = action;
	}
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Date getDue() {
		return due;
	}
	public void setDue(Date due) {
		this.due = due;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}		
}
