package com.bolas.bolas.dto;

import java.util.UUID;

public class AddMemberDTO {

	private UUID member;
	private UUID group;
	private SessionDTO session;
	
	public AddMemberDTO() {
		super();
	}

	public AddMemberDTO(UUID member, UUID group, SessionDTO session) {
		super();
		this.member = member;
		this.group = group;
		this.session = session;
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

	public SessionDTO getSession() {
		return session;
	}

	public void setSession(SessionDTO session) {
		this.session = session;
	}
	
}
