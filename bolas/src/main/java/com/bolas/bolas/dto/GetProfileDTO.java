package com.bolas.bolas.dto;

import java.util.UUID;

public class GetProfileDTO {

	private UUID id;
	private SessionDTO session;
	
	public GetProfileDTO() {
		super();
	}

	public GetProfileDTO(UUID id, SessionDTO session) {
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
