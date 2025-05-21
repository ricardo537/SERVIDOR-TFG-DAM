package com.bolas.bolas.dto;

import java.util.UUID;

import com.bolas.bolas.entity.Group;

public class GroupResumeDTO {

	private UUID id;
	private String name;
	private String img;
	
	public GroupResumeDTO() {
		super();
	}

	public GroupResumeDTO(UUID id, String name, String img) {
		super();
		this.id = id;
		this.name = name;
		this.img = img;
	}
	
	public GroupResumeDTO(Group group) {
		this.id = group.getId();
		this.name = group.getName();
		this.img = group.getImg();
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
