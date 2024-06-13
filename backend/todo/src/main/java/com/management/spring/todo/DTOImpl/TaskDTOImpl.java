package com.management.spring.todo.DTOImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.management.spring.todo.DTO.TaskDTO;
import com.management.spring.todo.model.DraftTask;
import com.management.spring.todo.model.Task;

@Component
public class TaskDTOImpl {
	
	public static final Map<String, String> STATUSES = new HashMap<>();
    static {
    	STATUSES.put("INPROGRESS", "In-Progress");
    	STATUSES.put("COMPLETED", "Completed");
    }
	
	public List<TaskDTO> convertTaskToTaskDTO(List<Task> tasks) {
        List<TaskDTO> taskDTOs = null;

        if(tasks.size() > 0) {
        	taskDTOs = tasks.stream()
                    .map(this::mapToTaskDTO)
                    .collect(Collectors.toList());
        }
		return taskDTOs;
	}
	
	public List<TaskDTO> convertDraftTaskToTaskDTO(List<DraftTask> draftTasks) {
		List<TaskDTO> taskDTOs = null;
        if(draftTasks.size() > 0) {
            taskDTOs = draftTasks.stream()
                    .map(this::mapToTaskDTO)
                    .collect(Collectors.toList());
        }
		return taskDTOs;
	}
	
	
	
    private TaskDTO mapToTaskDTO(Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setTaskId(task.getId());
        taskDTO.setTaskName(task.getTaskName());
        taskDTO.setUserId(task.getUserId());
        taskDTO.setStatus(task.getStatus());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setDate(task.getDate());
        taskDTO.setActive(task.getIsActive());
        return taskDTO;
    }

    private TaskDTO mapToTaskDTO(DraftTask draftTask) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(draftTask.getId());
        taskDTO.setTaskId(draftTask.getTaskId());
        taskDTO.setTaskName(draftTask.getTaskName());
        taskDTO.setUserId(draftTask.getUserId());
        taskDTO.setStatus(draftTask.getStatus());
        taskDTO.setDescription(draftTask.getDescription());
        taskDTO.setDate(draftTask.getDate());
        taskDTO.setDraftActive(draftTask.getIsDraftActive());
        return taskDTO;
    }

}
