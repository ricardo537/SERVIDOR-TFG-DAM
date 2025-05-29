package com.bolas.bolas.dto;

import java.util.UUID;

public class GetGroupEventDTO {

	private UUID id;
	private SessionDTO session;
	
	public GetGroupEventDTO() {
		super();
	}

	public GetGroupEventDTO(UUID id, SessionDTO session) {
		super();
		this.id = id;
		this.session = session;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public SessionDTO getSession() {
		return session;
	}

	public void setSession(SessionDTO session) {
		this.session = session;
	}
	
}
