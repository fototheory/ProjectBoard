package com.beans;

public class Role {
	private int id;
	private String name;
	
	public Role(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Role() { 
		
	}
	//setter/getter
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRoleName() {
		return name;
	}
	public void setRoleName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + "]";
	}	
}

