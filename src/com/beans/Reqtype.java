package com.beans;

public class Reqtype {
	//attributes
	private int id;
	private String name;
	
	public Reqtype(int id, String name){
		super();
		this.id = id;
		this.name = name;
	}

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

	@Override
	public String toString() {
		return "Reqtype [id=" + id + ", name=" + name + "]";
	}
	
}
