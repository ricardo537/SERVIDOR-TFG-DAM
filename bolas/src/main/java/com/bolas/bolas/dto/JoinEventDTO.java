package com.bolas.bolas.dto;

import java.util.UUID;

public class JoinEventDTO {

	private UUID eventId;
	private SessionDTO session;
	
	public JoinEventDTO() {
		super();
	}

	public JoinEventDTO(UUID eventId, SessionDTO session) {
		super();
		this.eventId = eventId;
		this.session = session;
	}

	public UUID getEventId() {
		return eventId;
	}

	public void setEventId(UUID eventId) {
		this.eventId = eventId;
	}

	public SessionDTO getSession() {
		return session;
	}

	public void setSession(SessionDTO session) {
		this.session = session;
	}
	
}
