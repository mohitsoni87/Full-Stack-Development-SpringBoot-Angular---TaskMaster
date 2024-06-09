package com.management.spring.todo.RestControllerImpl;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.management.spring.todo.RestController.TaskController;
import com.management.spring.todo.Service.TaskService;
import com.management.spring.todo.model.DraftTask;
import com.management.spring.todo.model.Task;

@Component
public class TaskControllerImpl implements TaskController{
	
	@Autowired
	private TaskService taskServiceImpl;
	
	@Override
	public ResponseEntity<Object> getTasks(String userName) throws BadRequestException {
		return taskServiceImpl.getTasks(userName);
	}

	@Override
	public ResponseEntity<Object> createTask(Task task) {
		return taskServiceImpl.createTask(task);
	}

	@Override
	public ResponseEntity<Object> completeTask(Integer taskId) {
		return taskServiceImpl.completeTask(taskId);
	}

	@Override
	public ResponseEntity<Object> updateTask(Task task) {
		return taskServiceImpl.updateTask(task);
	}

	@Override
	public ResponseEntity<Object> deleteTask(Integer taskId) {
		return taskServiceImpl.deleteTask(taskId);
	}

	@Override
	public ResponseEntity<Object> addDraft(DraftTask draftTask) {
		return taskServiceImpl.addDraft(draftTask);
	}
	
	@Override
	public ResponseEntity<Object> saveDraft(DraftTask draftTask) {
		return taskServiceImpl.saveDraft(draftTask);
	}

	@Override
	public ResponseEntity<Object> getDraftTasks(String userName) throws BadRequestException {
		return taskServiceImpl.getDraftTasks(userName);
	}

	@Override
	public ResponseEntity<Object> cancelDraftUpdate(Integer taskId) {
		return taskServiceImpl.cancelDraftUpdate(taskId);
	}


}
