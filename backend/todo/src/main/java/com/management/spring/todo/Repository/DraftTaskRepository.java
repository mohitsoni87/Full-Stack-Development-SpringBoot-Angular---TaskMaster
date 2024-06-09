package com.management.spring.todo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.management.spring.todo.model.DraftTask;

public interface DraftTaskRepository  extends JpaRepository<DraftTask, Integer>  {
	
	public List<DraftTask> findTasksByUserIdAndIsDraftActive(Integer userId, Boolean isDraftActive);
	
	public DraftTask findDraftTaskByTaskId(Integer taskId);
	
	@Modifying
	@Query("DELETE FROM DraftTask d WHERE d.taskId = ?1")
	public void deleteByTaskId(Integer taskId);

}
