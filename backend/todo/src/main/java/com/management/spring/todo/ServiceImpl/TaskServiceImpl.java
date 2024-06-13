package com.management.spring.todo.ServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.management.spring.todo.DTO.TaskDTO;
import com.management.spring.todo.DTO.UserDTO;
import com.management.spring.todo.DTOImpl.DraftTaskDTOTask;
import com.management.spring.todo.DTOImpl.TaskDTOImpl;
import com.management.spring.todo.Enum.TaskStatus;
import com.management.spring.todo.Repository.DraftTaskRepository;
import com.management.spring.todo.Repository.TaskRepository;
import com.management.spring.todo.Repository.UserRepository;
import com.management.spring.todo.Service.TaskService;
import com.management.spring.todo.model.DraftTask;
import com.management.spring.todo.model.Task;
import com.management.spring.todo.model.User;

import jakarta.transaction.Transactional;


@Service
public class TaskServiceImpl implements TaskService{
	
	private static final Logger log = LoggerFactory.getLogger(TaskServiceImpl.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private DraftTaskRepository draftTaskRepository;
	
	@Autowired
	private TaskDTOImpl taskDTOImpl;
	
	@Autowired
	private DraftTaskDTOTask draftTaskDTOTask;
	

	
	private HashMap<Integer, Task> tasks = new HashMap<Integer, Task>();
	private HashMap<Integer, User> users = new HashMap<Integer, User>();
	
	@Override
	public ResponseEntity<Object> getTasks(String userName){
        try {
			log.info("Get Tasks for {}", userName);
			User user = userRepository.findByUserName(userName);
			UserDTO userDTO = new UserDTO(user.getUsername(), user.getFirstName(), user.getLastName(), user.getId());
			if(userDTO == null) {
				log.info("User not found");
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
			List<Task> tasks = taskRepository.findTasksByUserId(userDTO.getId());
			List<TaskDTO> tasksDTO = taskDTOImpl.convertTaskToTaskDTO(tasks);
			return ResponseEntity.ok(tasksDTO);
        } catch (Exception ex) {
            log.error("Error occurred while fetching tasks " + ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
	}
	
	@Override
	public ResponseEntity<Object> getDraftTasks(String userName) {
        try {
			log.info("Get Draft Tasks for {}", userName);
			User user = userRepository.findByUserName(userName);
			UserDTO userDTO = new UserDTO(user.getUsername(), user.getFirstName(), user.getLastName(), user.getId());
			if(userDTO == null) {
				log.info("User not found");
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
			List<DraftTask> draftTasks = draftTaskRepository.findTasksByUserIdAndIsDraftActive(userDTO.getId(), true);
			List<TaskDTO> tasksDTO = taskDTOImpl.convertDraftTaskToTaskDTO(draftTasks);
			return ResponseEntity.ok(tasksDTO);
        } catch (Exception ex) {
            log.error("Error occurred while fetching tasks " + ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
	}
	
	@Override
	public ResponseEntity<Object> createTask(Task task) {
		try {
			if(task.getUserId() == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad Request: Either Task Id or User Id is null");
			}
			else if(tasks.containsKey(task.getId())) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Task already present");
			}
			task.setIsActive(true);
			taskRepository.save(task);
			return ResponseEntity.ok(task);
		}catch (Exception ex) {
            log.error("Internal Server Error {}", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
	}

	@Override
	public ResponseEntity<Object> completeTask(Integer taskId) {
		try {
			Task task = taskRepository.findById(taskId).get();
			if(task == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Task is not created yet");
			}
			task.setStatus(TaskStatus.COMPLETED);
			taskRepository.save(task);
			return ResponseEntity.ok(task);
		}catch (Exception ex) {
            log.error("Internal Server Error {}", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
	}

	@Override
	public ResponseEntity<Object> updateTask(Task task) {
		try {
			if(task.getId() == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Task is not created yet");
			}
			taskRepository.save(task);
			return ResponseEntity.ok(task);
		}catch (Exception ex) {
            log.error("Internal Server Error {}", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
	}

	@Override
	@Transactional
	public ResponseEntity<Object> deleteTask(Integer taskId) {
		try {
			Task task = taskRepository.findById(taskId).get();
			if(task == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Task is not created yet");
			}
			taskRepository.deleteById(taskId);
			return ResponseEntity.status(204).body("Task Deleted");
		}catch (Exception ex) {
            log.error("Internal Server Error {}", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
	}
	
	@Override
	public void registerTasksAndUsers(List<Task> userTasks, List<User> usersToDo) {
		for(Task task: userTasks) {
			tasks.put(task.getId(), task);
		}
		
		for(User user: usersToDo) {
			users.put(user.getId(), user);
		}
		
	}

	@Override
	public ResponseEntity<Object> addDraft(DraftTask draftTask) {
		try {
			if(draftTask.getTaskId() == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Task is not created yet");
			}
			
			Task task = taskRepository.findTaskById(draftTask.getTaskId());
			
			if(task != null) {
				DraftTask draftTaskCheck = draftTaskRepository.findDraftTaskByTaskId(draftTask.getTaskId());
				if(draftTaskCheck!= null) {
					//Draft Task already present
					draftTaskCheck.setDescription(draftTask.getDescription());
					draftTaskCheck.setStatus(draftTask.getStatus());
					draftTaskRepository.save(draftTaskCheck);
					return ResponseEntity.ok(draftTaskCheck);
				}
				
				//Making original task inactive
				task.setIsActive(false);
				taskRepository.save(task);
				
				
				//Adding new Draft Task
				draftTask.setIsDraftActive(true);
				draftTaskRepository.save(draftTask);
				return ResponseEntity.ok(draftTask);
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Task is either not created yet or already non-active. Can't add Draft.");
			}
		}catch (Exception ex) {
            log.error("Internal Server Error {}", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
	}

	@Override
	@Transactional
	public ResponseEntity<Object> saveDraft(DraftTask draftTask) {
		try {
			Task task = taskRepository.findTaskById(draftTask.getTaskId());
			if(task != null && !task.getIsActive()) {
				//Saving Draft Task to Task Table
				Task saveTask = draftTaskDTOTask.saveDraftTask(draftTask);
				taskRepository.save(saveTask);
				
				//Deleting Draft Task
				draftTaskRepository.deleteByTaskId(draftTask.getTaskId());
				return ResponseEntity.ok(saveTask);
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Task either is not created yet or is already active. Can't save Draft.");
			}
		}catch (Exception ex) {
            log.error("Internal Server Error {}", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
	}

	@Override
	@Transactional
	public ResponseEntity<Object> cancelDraftUpdate(Integer taskId) {
		try {
			Task task = taskRepository.findTaskById(taskId);
			if(task != null && !task.getIsActive()) {
				//Deleting Draft Task
				draftTaskRepository.deleteByTaskId(taskId);
				
				//Activate Original record
				task.setIsActive(true);
				return ResponseEntity.ok(task);
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Task either is not created yet or is already active. Can't save Draft.");
			}
		}catch (Exception ex) {
            log.error("Internal Server Error {}", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
	}

}
