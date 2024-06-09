package com.management.spring.todo.model;

import java.time.LocalDate;

import com.management.spring.todo.Enum.TaskStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "tasks") // Table name in the database
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_sequence")
    @SequenceGenerator(name = "task_sequence", sequenceName = "task_sequence", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "task_name")
    private String taskName;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "description")
    private String description;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private TaskStatus status;
    
    @Column(name = "is_active")
    private Boolean isActive;


    public Task() {
        // Default constructor
    }

	public Task(Integer id, Integer userId, String taskName,  String description, LocalDate date) {
		super();
		this.id = id;
		this.taskName = taskName;
		this.description = description;
		this.date = date;
		this.userId = userId;
	}
	
	@Override
	public String toString() {
		return "Task [id=" + id + ", taskName=" + taskName + ", description=" + description + ", date=" + date + "]";
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus taskStatus) {
		this.status = taskStatus;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	
}
