package com.beans;

public class Requirement {
	//attributes
	private int id;
	private String description;
	private int weight;
	private int projectId;
	private int reqtypeId;
	
	public Requirement(int id, String description, int weight, int projectId, int reqtypeId) {
		super();
		this.id = id;
		this.description = description;
		this.weight = weight;
		this.projectId = projectId;
		this.reqtypeId = reqtypeId;
	}

	//setter/getter
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getReqtypeId() {
		return reqtypeId;
	}

	public void setReqtypeId(int reqtypeId) {
		this.reqtypeId = reqtypeId;
	}

	@Override
	public String toString() {
		return "Requirement [id=" + id + ", description=" + description
				+ ", weight=" + weight + ", projectId=" + projectId
				+ ", reqtypeId=" + reqtypeId + "]";
	}
}
