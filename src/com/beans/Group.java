package com.beans;

import java.util.Date;

public class Group {
	//attributes
		private int id;
		private Date dateFormed;
		private String name;
		private int taskId;
		
		public Group(int id, String name, Date dateFormed, int taskId) {
			super();
			this.id = id;
			this.dateFormed = dateFormed;
			this.name = name;
			this.taskId = taskId;
		}		
		
		//setter/getter
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public Date getDateFormed() {
			return dateFormed;
		}
		public void setDateFormed(Date dateFormed) {
			this.dateFormed = dateFormed;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getTaskId() {
			return taskId;
		}
		public void setTaskId(int taskId) {
			this.taskId = taskId;
		}
		
		@Override
		public String toString() {
			return "Group [id=" + id + ", dateFormed=" + dateFormed + ", name="
					+ name + ", taskId=" + taskId + "]";
		}
		
}

