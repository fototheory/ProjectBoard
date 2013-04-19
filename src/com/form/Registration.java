package com.form;

/*import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;*/



public class Registration {

	private String fname;

	private String lname;
	//@NotEmpty
	//@Size(min = 4, max = 20)
	private String password;
	// @NotEmpty
	private String confirmPassword;
	// @NotEmpty
	//@Email
	private String email;
	private int role;


	public void setfname(String fname) {
		this.fname = fname;
	}

	public String getfname() {
		return fname;
	}

	public void setlname(String lname) {
		this.lname = lname;
	}

	public String getlname() {
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

}
