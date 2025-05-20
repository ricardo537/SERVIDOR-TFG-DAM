package com.bolas.bolas.dto;

import java.util.UUID;

public class FollowDTO {

	private UUID userId;
	private SessionDTO session;
	
	public FollowDTO() {
		super();
	}

	public FollowDTO(UUID userId, SessionDTO session) {
		super();
		this.userId = userId;
		this.session = session;
	}

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	public SessionDTO getSession() {
		return session;
	}

	public void setSession(SessionDTO session) {
		this.session = session;
	}
	
}
