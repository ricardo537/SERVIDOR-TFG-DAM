package com.bolas.bolas.dto;

import java.util.UUID;

import com.bolas.bolas.entity.User;

public class UserResumeDTO {

	private UUID id;
	private String name;
	private String img;
	
	public UserResumeDTO() {
		super();
	}

	public UserResumeDTO(UUID id, String name, String img) {
		super();
		this.id = id;
		this.name = name;
		this.img = img;
	}
	
	public UserResumeDTO(User user) {
		super();
		this.id = user.getId();
		this.name = user.getName();
		this.img = user.getImg();
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

}
