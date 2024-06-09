package com.management.spring.todo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.management.spring.todo.model.Task;


@Repository
public interface TaskRepository extends JpaRepository<Task, Integer>  {
	
	
	//@Query(value="select NEW com.management.spring.todo.DTO.TaskDTO(t.id, t.taskName, t.userId, t.description, t.date, s.statusName) from  User u, Task t, Status s where u.id = t.userId and t.status = s.statusId and u.id =:user_id")
	//public List<TaskDTO> findTasksByUserId(@Param("user_id") Integer user_id);
	
	public List<Task> findTasksByUserId(Integer userId);
	
	
	public Task findTaskById(Integer taskId);
	
	//@Query(value="select NEW com.management.spring.todo.DTO.TaskDTO(q.id, q.topic, q.views, ud.username, ur.points) from from users u, tasks t, status s where u.id = t.user_id and t.status = s.status_id and u.id =:user_id")
	//public List<Task> findTasksByUserId(@Param("user_id") Integer user_id);

}
