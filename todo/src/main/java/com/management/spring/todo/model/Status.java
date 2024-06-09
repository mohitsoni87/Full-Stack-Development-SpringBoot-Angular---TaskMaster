package com.management.spring.todo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "status") // Table name in the database
public class Status {
	
	@Id
	@Column(name = "status_id")
	private String statusId;
	
	
	@Column(name = "status_name")
	private String statusName;


	public String getStatus() {
		return statusId;
	}


	public void setStatus(String status) {
		this.statusId = status;
	}


	public String getStatusName() {
		return statusName;
	}


	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	
	

}
