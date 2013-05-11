package com.form;

public class ForgotPassword {
	
	private String fname;
	private String lname;
	
	private String password;
	
	
	private String email;
	
	public String getFname() {
		return fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	
	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}
	

}
