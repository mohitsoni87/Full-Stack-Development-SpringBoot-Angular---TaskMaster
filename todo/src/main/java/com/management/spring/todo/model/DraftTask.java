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
@Table(name = "draft_tasks")
public class DraftTask {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "draft_task_sequence")
    @SequenceGenerator(name = "draft_task_sequence", sequenceName = "draft_task_sequence", allocationSize = 1)
    private Integer id;

    @Column(name = "task_name", length = 255)
    private String taskName;

    @Column(name = "user_id")
    private Integer userId;
    
    @Column(name = "task_id")
    private Integer taskId;

    @Column(name = "description")
    private String description;

    @Column(name = "date")
    private LocalDate date;
    
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private TaskStatus status;
    
    @Column(name = "is_draft_active")
    private Boolean isDraftActive;

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public Integer getTaskId() {
		return taskId;
	}
    
	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
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

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	public Boolean getIsDraftActive() {
		return isDraftActive;
	}

	public void setIsDraftActive(Boolean isDraftActive) {
		this.isDraftActive = isDraftActive;
	}

}
