package com.bolas.bolas.dto;

public class DeleteDTO {
	
	private String email;
	private String password;
	
	
	public DeleteDTO(String email, String password ) {
		super();
		this.email = email;
		this.password = password;
	
	}

	public DeleteDTO() {
		super();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
}
