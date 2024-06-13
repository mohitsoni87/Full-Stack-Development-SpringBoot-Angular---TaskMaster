package com.management.spring.todo.ServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.management.spring.todo.Repository.UserRepository;
import com.management.spring.todo.Service.AuthenticationService;
import com.management.spring.todo.model.User;

@Component
public class AuthenticationServiceImpl implements AuthenticationService{

	private static final Logger log = LoggerFactory.getLogger(AuthenticationServiceImpl.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public ResponseEntity<Object> authenticateUser(User user) {
		try {
			User userResult = userRepository.findByUserName(user.getUsername());
			if(userResult != null) {
				if(userResult.getPassword().equals(user.getPassword())) {
					return ResponseEntity.ok(userResult);
				}else {
					return ResponseEntity.status(400).body("Invalid Credentials");	
				}
			}else {
				return ResponseEntity.status(400).body("User not found");
			}
			
		}catch(Exception e) {
			log.error("Unable to process login..");
			return ResponseEntity.status(500).body("Internal Server Error");
		}

	}

	@Override
	public ResponseEntity<Object> registerUser(User user) {
		if(user.getUsername() != null && user.getPassword() != null) {
			userRepository.save(user);
			user = userRepository.findByUserName(user.getUsername());
			return ResponseEntity.ok(user);
		}
		return null;
	}

	@Override
	public ResponseEntity<Object> checkUserNameAvailability(String userName) {
		User user = userRepository.findByUserName(userName);
		if(user != null) {
			//return new EntityResponse(400, "Username is taken").getResponseEntity();
			return ResponseEntity.status(400).body("Username already taken.");
		}else {
			return ResponseEntity.status(204).body("User name is available");
		}
		
	}

}
