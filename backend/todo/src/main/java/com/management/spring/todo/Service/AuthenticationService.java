package com.management.spring.todo.Service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.management.spring.todo.model.User;

@Service
public interface AuthenticationService{
	
	public ResponseEntity<Object> authenticateUser(User user);

	public ResponseEntity<Object> registerUser(User user);

	public ResponseEntity<Object> checkUserNameAvailability(String userName);

}
