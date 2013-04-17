package com.beans;

import java.util.Date;

public class Project {
	//attributes
	private int id;
	private String title;
	private String desc;
	private boolean isArchived;
	private String status;
	private int manHours;
	private Date due;
	private int reqId;
	private int dispId;
	private int groupId;
	private int forumId;
	private int leadId;
	private int negId;
	private int capId;
	private int sponsorId;
	
	public Project(int id, String title, String desc, boolean isArchived,
			String status, int manHours, Date due, int reqId, int dispId,
			int groupId, int forumId, int leadId, int negId, int capId,
			int sponsorId) {
		super();
		this.id = id;
		this.title = title;
		this.desc = desc;
		this.isArchived = isArchived;
		this.status = status;
		this.manHours = manHours;
		this.due = due;
		this.reqId = reqId;
		this.dispId = dispId;
		this.groupId = groupId;
		this.forumId = forumId;
		this.leadId = leadId;
		this.negId = negId;
		this.capId = capId;
		this.sponsorId = sponsorId;
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

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public boolean isArchived() {
		return isArchived;
	}

	public void setArchived(boolean isArchived) {
		this.isArchived = isArchived;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getManHours() {
		return manHours;
	}

	public void setManHours(int manHours) {
		this.manHours = manHours;
	}

	public Date getDue() {
		return due;
	}

	public void setDue(Date due) {
		this.due = due;
	}

	public int getReqId() {
		return reqId;
	}

	public void setReqId(int reqId) {
		this.reqId = reqId;
	}

	public int getDispId() {
		return dispId;
	}

	public void setDispId(int dispId) {
		this.dispId = dispId;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public int getForumId() {
		return forumId;
	}

	public void setForumId(int forumId) {
		this.forumId = forumId;
	}

	public int getLeadId() {
		return leadId;
	}

	public void setLeadId(int leadId) {
		this.leadId = leadId;
	}

	public int getNegId() {
		return negId;
	}

	public void setNegId(int negId) {
		this.negId = negId;
	}

	public int getCapId() {
		return capId;
	}

	public void setCapId(int capId) {
		this.capId = capId;
	}

	public int getSponsorId() {
		return sponsorId;
	}

	public void setSponsorId(int sponsorId) {
		this.sponsorId = sponsorId;
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", title=" + title + ", desc=" + desc
				+ ", isArchived=" + isArchived + ", status=" + status
				+ ", manHours=" + manHours + ", due=" + due + ", reqId="
				+ reqId + ", dispId=" + dispId + ", groupId=" + groupId
				+ ", forumId=" + forumId + ", leadId=" + leadId + ", negId="
				+ negId + ", capId=" + capId + ", sponsorId=" + sponsorId + "]";
	}
}
