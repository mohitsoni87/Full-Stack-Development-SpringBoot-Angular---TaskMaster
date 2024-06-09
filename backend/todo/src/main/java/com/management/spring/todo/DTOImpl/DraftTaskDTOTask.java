package com.management.spring.todo.DTOImpl;


import org.springframework.stereotype.Component;

import com.management.spring.todo.model.DraftTask;
import com.management.spring.todo.model.Task;

@Component
public class DraftTaskDTOTask {
	
	public Task saveDraftTask(DraftTask draftTask) {
		Task task = convertDraftToTask(draftTask);
		return task;
	}
	
    private Task convertDraftToTask(DraftTask draftTask) {
    	Task task = new Task();
    	task.setId(draftTask.getTaskId());
    	task.setTaskName(draftTask.getTaskName());
    	task.setUserId(draftTask.getUserId());
    	task.setStatus(draftTask.getStatus());
    	task.setDescription(draftTask.getDescription());
    	task.setDate(draftTask.getDate());
    	task.setIsActive(true);
		return task;
	}


}
