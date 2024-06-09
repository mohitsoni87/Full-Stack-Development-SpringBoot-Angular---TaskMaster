package com.management.spring.todo.RestController;


import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.management.spring.todo.model.DraftTask;
import com.management.spring.todo.model.Task;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public interface TaskController {
	
	@GetMapping("/getTasks/{userName}")
	public ResponseEntity<Object> getTasks(@PathVariable String userName) throws BadRequestException;
	
	@GetMapping("/getDraftTasks/{userName}")
	public ResponseEntity<Object> getDraftTasks(@PathVariable String userName) throws BadRequestException;
	
	@PostMapping("/createTask")
	public ResponseEntity<Object> createTask(@RequestBody Task task);
	
	@GetMapping("/completeTask/{taskId}")
	public ResponseEntity<Object> completeTask(@PathVariable Integer taskId);
	
	@PutMapping("/updateTask")
	public ResponseEntity<Object> updateTask(@RequestBody Task task);
	
	@DeleteMapping("/deleteTask/{taskId}")
	public ResponseEntity<Object> deleteTask(@PathVariable Integer taskId);
	
	@PostMapping("/addDraft")
	public ResponseEntity<Object> addDraft(@RequestBody DraftTask draftTask);
	
	@PostMapping("/saveDraft")
	public ResponseEntity<Object> saveDraft(@RequestBody DraftTask draftTask);
	
	@GetMapping("/cancelDraftUpdate/{taskId}")
	public ResponseEntity<Object> cancelDraftUpdate(@PathVariable Integer taskId);
	

}
