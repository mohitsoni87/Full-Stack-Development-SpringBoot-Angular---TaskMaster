package com.management.spring.todo.DTO;

import java.time.LocalDate;


public class TaskDTO {
	
    private Integer id;

    private String taskName;

    private Integer userId;

    private String description;

    private LocalDate date;

    private String status;
    
    private boolean isActive;
    
    private boolean isDraftActive;
    
    private Integer taskId;
    
	public TaskDTO(Integer id, String taskName, Integer userId, String description, LocalDate date, String status,
			boolean isActive, boolean isDraftActive, Integer taskId) {
		super();
		this.id = id;
		this.taskName = taskName;
		this.userId = userId;
		this.description = description;
		this.date = date;
		this.status = status;
		this.isActive = isActive;
		this.isDraftActive = isDraftActive;
		this.taskId = taskId;
	}

	public TaskDTO() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public boolean isDraftActive() {
		return isDraftActive;
	}

	public void setDraftActive(boolean isDraftActive) {
		this.isDraftActive = isDraftActive;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}
	
	
}
