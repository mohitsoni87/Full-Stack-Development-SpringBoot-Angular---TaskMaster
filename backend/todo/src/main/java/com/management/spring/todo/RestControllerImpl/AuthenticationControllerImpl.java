package com.management.spring.todo.RestControllerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.management.spring.todo.RestController.AuthenticationController;
import com.management.spring.todo.Service.AuthenticationService;
import com.management.spring.todo.Service.TaskService;
import com.management.spring.todo.model.Task;
import com.management.spring.todo.model.User;

@Component
public class AuthenticationControllerImpl implements AuthenticationController{
	
	@Autowired
	private AuthenticationService authenticationService;

	@Override
	public ResponseEntity<Object> authenticateUser(User user) {
		return authenticationService.authenticateUser(user);
	}

	@Override
	public ResponseEntity<Object> registerUser(User user) {
		return authenticationService.registerUser(user);
	}

	@Override
	public ResponseEntity<Object> checkUserNameAvailability(String userName) {
		return authenticationService.checkUserNameAvailability(userName);
	}
	
}
