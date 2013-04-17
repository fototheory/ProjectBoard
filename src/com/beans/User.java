package com.beans;

/**
 * <code>User</code> class follows a table structure of user table
 * 1. Stores a user data from database in dao classes
 * 2. Stores data retrieved from JSP in controller and service classes
 * @author Yumiko Iwai
 * @version 1.0
 *
 */
public class User {
	//attributes
	private int id;
	private String fname;
	private String lname;
	private String email;
	private String password;
	private int isVerified;
	private int hasProfile;
	private int roleId;
	private int profileId;
	private int disciplineId;
	
	/**
	 * default constructor
	 * session and controller utilize this constructor to create an empty user object 
	 */
	public User() {
	}
	
	/**
	 * set all the attributes of the class
	 * @param all the values for the attributes
	 */
	public User(int id, String fname, String lname, String email, String password, 
			int isVerified, int hasProfile, int roleId, int profileId, int disciplineId) {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.password = password;
		this.isVerified = isVerified;
		this.hasProfile = hasProfile;
		this.roleId = roleId;
		this.profileId = profileId;
		this.disciplineId = disciplineId;
	}
	
	//setter/getter
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFname() {
		return fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int isVerified() {
		return isVerified;
	}
	public void setVerified(int isVerified) {
		this.isVerified = isVerified;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public int getHasProfile() {
		return hasProfile;
	}

	public void setHasProfile(int hasProfile) {
		this.hasProfile = hasProfile;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getProfileId() {
		return profileId;
	}

	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}

	public int getIsVerified() {
		return isVerified;
	}

	public void setIsVerified(int isVerified) {
		this.isVerified = isVerified;
	}

	public int getDisciplineId() {
		return disciplineId;
	}

	public void setDisciplineId(int disciplineId) {
		this.disciplineId = disciplineId;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", fname=" + fname + ", lname=" + lname
				+ ", email=" + email + ", password=" + password
				+ ", isVerified=" + isVerified + ", hasProfile="
				+ hasProfile + ", roleId=" + roleId + ", profileId="
				+ profileId + ", disciplineId=" + disciplineId + "]";
	}

}
