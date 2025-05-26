package com.bolas.bolas.dto;

import java.util.UUID;

public class UnjoinEventDTO {

	private UUID event;
	private SessionDTO session;
	
	public UnjoinEventDTO(UUID event, SessionDTO session) {
		super();
		this.event = event;
		this.session = session;
	}

	public UnjoinEventDTO() {
		super();
	}

	public UUID getEvent() {
		return event;
	}

	public void setEvent(UUID event) {
		this.event = event;
	}

	public SessionDTO getSession() {
		return session;
	}

	public void setSession(SessionDTO session) {
		this.session = session;
	}
	
}
