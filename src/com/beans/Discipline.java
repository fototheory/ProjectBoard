package com.beans;

public class Discipline {
	private int id;
	private String name;
	
	public Discipline() {}
			
	public Discipline(int id, String name) {
		this.id = id;
		this.name = name;
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
	public String getDispName() {
		return name;
	}
	public void setDispName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + "]";
	}	
}
