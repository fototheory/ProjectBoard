package com.form;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ProjectInfo {
	private int projectId;
	private String title;
	private String desc;
	private int disp;
	private String due;
	private String action;
	
	public ProjectInfo() {
		
	}
	
	public ProjectInfo(int projectId, String title, String desc, int disp,
			String due, String action) {
		super();
		this.projectId = projectId;
		this.title = title;
		this.desc = desc;
		this.disp = disp;
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
	public int getDisp() {
		return disp;
	}
	public void setDisp(int disp) {
		this.disp = disp;
	}
	public String getDue() {
		return due;
	}
	public void setDue(String due) {
		this.due = due;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}		
}
