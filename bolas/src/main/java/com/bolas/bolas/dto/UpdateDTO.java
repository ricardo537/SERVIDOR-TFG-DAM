package com.bolas.bolas.dto;

import com.bolas.bolas.entity.User;

public class UpdateDTO {

	private String email;
	private String password;
	private String name;
	private String description;
	private SessionDTO session;
	
	public UpdateDTO() {
		super();
	}

	public UpdateDTO(String email, String password, String name, String description, SessionDTO session) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
		this.description = description;
		this.session = session;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public SessionDTO getSession() {
		return session;
	}

	public void setSession(SessionDTO session) {
		this.session = session;
	}

	//Falta cambiarlo
	public User updateUser(User user) {
		if (!email.equals("") && !user.getEmail().equals(email)) {
			user.setEmail(email);
			session.setEmail(email);
		}
		if (!password.equals("") && !user.getPassword().equals(password)) {
			user.setPassword(password);
			session.setPassword(password);
		}
		if (!name.equals("") && !user.getName().equals(name)) {
			user.setName(name);
		}
		if (!description.equals("") && !description.equals(user.getDescription())) {
			user.setDescription(description);
		}
		return user;
	}
}
