package com.beans;

public class Profile {
	//attributes
	private int id;
	private String company;
	private String skills;
	private String phone;
	
	public Profile(){}
	
	public Profile(int id, String company, String skills, String phone) {
		super();
		this.id = id;
		this.company = company;
		this.skills = skills;
		this.phone = phone;
	}
	
	//setter/getter
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getSkills() {
		return skills;
	}
	public void setSkills(String skills) {
		this.skills = skills;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Profile [id=" + id + ", company=" + company + ", skills="
				+ skills + ", phone=" + phone + "]";
	}	
}