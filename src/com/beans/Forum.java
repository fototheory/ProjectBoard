package com.beans;

public class Forum {
	private int id;
	private int entryId;
	
	public Forum(int id, int entry_id) {
		super();
		this.id = id;
		this.entryId = entry_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEntryId() {
		return entryId;
	}

	public void setEntryId(int entry_id) {
		this.entryId = entry_id;
	}

	@Override
	public String toString() {
		return "Forum [id=" + id + ", entryId=" + entryId + "]";
	}	
	
}