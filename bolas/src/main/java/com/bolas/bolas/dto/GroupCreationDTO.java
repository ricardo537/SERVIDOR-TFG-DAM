package com.bolas.bolas.dto;

import com.bolas.bolas.entity.Group;
import com.bolas.bolas.entity.User;

public class GroupCreationDTO {

	private String name;
	private SessionDTO session;
	
	public GroupCreationDTO() {
		super();
	}

	public GroupCreationDTO(String name, SessionDTO session) {
		super();
		this.name = name;
		this.session = session;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SessionDTO getSession() {
		return session;
	}

	public void setSession(SessionDTO session) {
		this.session = session;
	}

	public Group toGroup(User creator) {
		Group group = new Group();
		group.setCreator(creator.getName());
		group.setImg("");
		group.setName(this.name);
		return group;
	}
}
