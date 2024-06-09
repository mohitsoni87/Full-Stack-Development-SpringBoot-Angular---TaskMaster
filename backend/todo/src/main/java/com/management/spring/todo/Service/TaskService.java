package com.management.spring.todo.Service;


import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.management.spring.todo.model.DraftTask;
import com.management.spring.todo.model.Task;
import com.management.spring.todo.model.User;


@Service
public interface TaskService {
	
	public ResponseEntity<Object> createTask(Task task);
	
	public ResponseEntity<Object> completeTask(Integer taskId);
	
	public ResponseEntity<Object> updateTask(Task task);
	
	public ResponseEntity<Object> deleteTask(Integer taskId);

	void registerTasksAndUsers(List<Task> tasks, List<User> users);
	
	public ResponseEntity<Object> getTasks(String userName);

	public ResponseEntity<Object> addDraft(DraftTask draftTask);

	public ResponseEntity<Object> saveDraft(DraftTask draftTask);

	public ResponseEntity<Object> getDraftTasks(String userName);

	public ResponseEntity<Object> cancelDraftUpdate(Integer taskId);
 	

}
