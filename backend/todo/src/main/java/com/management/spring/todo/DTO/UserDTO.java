package com.management.spring.todo.DTO;


public class UserDTO {
	
	private Integer id;
	
    private String userName;

    private String firstName;

    private String lastName;
    

	public UserDTO(String userName, String firstName, String lastName, Integer id) {
		super();
		this.id = id;
		this.userName = userName;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
    
    

}
