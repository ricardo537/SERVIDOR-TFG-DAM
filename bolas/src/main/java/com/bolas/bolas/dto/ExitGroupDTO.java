package com.bolas.bolas.dto;

import java.util.UUID;

public class ExitGroupDTO {

	private UUID group;
	private SessionDTO session;
	
	public ExitGroupDTO() {
		super();
	}

	public ExitGroupDTO(UUID group, SessionDTO session) {
		super();
		this.group = group;
		this.session = session;
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
