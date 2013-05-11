package com.form;

public class AdminUserForm {
	private int id;
	private String fname;
	private String lname;
	private boolean isverified;
	private boolean hasprofile;
	//@NotEmpty
	//@Size(min = 4, max = 20)
	private String password;
	// @NotEmpty
	private String confirmPassword;
	//@NotEmpty
	//@Email
	private String email;
	private String company;
	private String phone;
	private String skills;
	private int role;
	private int discipline;
	private int profile;
	private int group;
	
	public AdminUserForm() {}
	
	public AdminUserForm(int id, String fname, String lname,
			boolean isverified, boolean hasprofile, String password,
			String confirmPassword, String email, String company, String phone,
			String skills, int role, int discipline, int profile, int group) {
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.isverified = isverified;
		this.hasprofile = hasprofile;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.email = email;
		this.company = company;
		this.phone = phone;
		this.skills = skills;
		this.role = role;
		this.discipline = discipline;
		this.profile = profile;
		this.group = group;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isIsverified() {
		return isverified;
	}

	public void setIsverified(boolean isverified) {
		this.isverified = isverified;
	}

	public boolean isHasprofile() {
		return hasprofile;
	}

	public void setHasprofile(boolean hasprofile) {
		this.hasprofile = hasprofile;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public int getProfile() {
		return profile;
	}

	public void setProfile(int profile) {
		this.profile = profile;
	}

	public int getGroup() {
		return group;
	}

	public void setGroup(int group) {
		this.group = group;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getFname() {
		return fname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getLname() {
		return lname;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public int getDiscipline() {
		return discipline;
	}

	public void setDiscipline(int discipline) {
		this.discipline = discipline;
	}
}
