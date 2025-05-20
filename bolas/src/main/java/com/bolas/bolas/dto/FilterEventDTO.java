package com.bolas.bolas.dto;

import java.time.LocalDateTime;

public class FilterEventDTO {

	private LocalDateTime startDate;
	private String type;
	private String gender;
	private String typeParticipant;
	private String sport;
	private SessionDTO session;
	
	public FilterEventDTO() {
		super();
	}

	public FilterEventDTO(LocalDateTime startDate, String type, String gender, String typeParticipant,
			String sport, SessionDTO session) {
		super();
		this.startDate = startDate;
		this.type = type;
		this.gender = gender;
		this.typeParticipant = typeParticipant;
		this.sport = sport;
		this.session = session;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getTypeParticipant() {
		return typeParticipant;
	}

	public void setTypeParticipant(String typeParticipant) {
		this.typeParticipant = typeParticipant;
	}

	public String getSport() {
		return sport;
	}

	public void setSport(String sport) {
		this.sport = sport;
	}

	public SessionDTO getSession() {
		return session;
	}

	public void setSession(SessionDTO session) {
		this.session = session;
	}
	
}
