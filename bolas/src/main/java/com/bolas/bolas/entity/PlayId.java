package com.bolas.bolas.entity;

import java.io.Serializable;
import java.util.UUID;

public class PlayId implements Serializable {

	private UUID member;
	private UUID group;
	
	public PlayId() {
		super();
	}

	public PlayId(UUID member, UUID group) {
		super();
		this.member = member;
		this.group = group;
	}

	public UUID getMember() {
		return member;
	}

	public void setMember(UUID member) {
		this.member = member;
	}

	public UUID getGroup() {
		return group;
	}

	public void setGroup(UUID group) {
		this.group = group;
	}

}
