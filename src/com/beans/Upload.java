package com.beans;

import java.util.Date;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class Upload {
	//attributes
	private int id;
	private String path;
	private String description;
	private Date datetime;
	private int projectId;
	
	public Upload(int id, String path, String description, Date datetime,
			int projectId) {
		super();
		this.id = id;
		this.path = path;
		this.description = description;
		this.datetime = datetime;
		this.projectId = projectId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	@Override
	public String toString() {
		return "Upload [id=" + id + ", path=" + path + ", description="
				+ description + ", datetime=" + datetime + ", projectId="
				+ projectId + "]";
	}
	
}
