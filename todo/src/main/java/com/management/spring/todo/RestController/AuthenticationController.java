package com.management.spring.todo.RestController;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.management.spring.todo.model.User;

@RestController
public interface AuthenticationController {
	
	@CrossOrigin(origins = "http://localhost:4200/")
	@PostMapping("/user/authenticate")
	public ResponseEntity<Object> authenticateUser(@RequestBody User user);
	
	
	@PostMapping("/user/register")
	public ResponseEntity<Object> registerUser(@RequestBody User user);
	
	@GetMapping("/user/checkUserName/{userName}")
	public ResponseEntity<Object> checkUserNameAvailability(@PathVariable String userName);
}
