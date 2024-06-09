package com.management.spring.todo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.management.spring.todo.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer>  {
	
	public User findByUserName(String username);

}
