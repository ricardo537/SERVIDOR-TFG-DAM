package com.bolas.bolas.dto;

import java.util.UUID;

import com.bolas.bolas.entity.Group;

public class UpdateGroupDTO {

	private UUID group;
	private String name;
	
	public UpdateGroupDTO() {
		super();
	}

	public UpdateGroupDTO(UUID group, String name) {
		super();
		this.group = group;
		this.name = name;
	}

	public UUID getGroup() {
		return group;
	}

	public void setGroup(UUID group) {
		this.group = group;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Group update(Group groupToChange) {
		if (name != null && !name.equals("")) {
			groupToChange.setName(name);
		}
		return groupToChange;
	}
	
}
