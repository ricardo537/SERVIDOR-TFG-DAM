package com.bolas.bolas.dto;

import java.time.LocalDateTime;

public class EventPublishDTO {

	private String name;
	private String description;
	private String address;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private String type;
	private String sport;
	private Integer minParticipants;
	private Integer maxParticipants;
	private SessionDTO session;
	private Double price;
	private String gender;
	private String typeParticipant;
	
	public EventPublishDTO() {
		super();
	}

	public EventPublishDTO(String name, String description, String address, LocalDateTime startDate,
			LocalDateTime endDate, String type, String sport, Integer minParticipants, Integer maxParticipants,
			SessionDTO session, Double price, String gender, String typeParticipant) {
		super();
		this.name = name;
		this.description = description;
		this.address = address;
		this.startDate = startDate;
		this.endDate = endDate;
		this.type = type;
		this.sport = sport;
		this.minParticipants = minParticipants;
		this.maxParticipants = maxParticipants;
		this.session = session;
		this.price = price;
		this.gender = gender;
		this.typeParticipant = typeParticipant;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSport() {
		return sport;
	}

	public void setSport(String sport) {
		this.sport = sport;
	}

	public Integer getMinParticipants() {
		return minParticipants;
	}

	public void setMinParticipants(Integer minParticipants) {
		this.minParticipants = minParticipants;
	}

	public Integer getMaxParticipants() {
		return maxParticipants;
	}

	public void setMaxParticipants(Integer maxParticipants) {
		this.maxParticipants = maxParticipants;
	}

	public SessionDTO getSession() {
		return session;
	}

	public void setSession(SessionDTO session) {
		this.session = session;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
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

}
