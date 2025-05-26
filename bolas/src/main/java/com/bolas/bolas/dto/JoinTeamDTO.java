package com.bolas.bolas.dto;

import java.util.UUID;

public class JoinTeamDTO {

	private UUID event;
	private UUID group;
	private SessionDTO session;
	
	public JoinTeamDTO() {
		super();
	}

	public JoinTeamDTO(UUID event, UUID group, SessionDTO session) {
		super();
		this.event = event;
		this.group = group;
		this.session = session;
	}

	public UUID getEvent() {
		return event;
	}

	public void setEvent(UUID event) {
		this.event = event;
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
