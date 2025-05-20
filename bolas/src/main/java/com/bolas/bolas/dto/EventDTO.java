package com.bolas.bolas.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.bolas.bolas.entity.Event;

public class EventDTO {
	
	private UUID id;
	private String name;
	private String description;
	private String address;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private LocalDateTime creationDate;
	private String type;
	private String sport;
	private Integer minParticipants;
	private Integer maxParticipants;
	private Double price;
	private String gender;
	private String typeParticipant;
	private String creatorName;
	private int participants;
	
	public EventDTO() {
		super();
	}

	public EventDTO(UUID id, String name, String description, String address, LocalDateTime startDate,
			LocalDateTime endDate, LocalDateTime creationDate, String type, String sport, Integer minParticipants,
			Integer maxParticipants, Double price, String gender, String typeParticipant, String creatorName,
			int participants) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.address = address;
		this.startDate = startDate;
		this.endDate = endDate;
		this.creationDate = creationDate;
		this.type = type;
		this.sport = sport;
		this.minParticipants = minParticipants;
		this.maxParticipants = maxParticipants;
		this.price = price;
		this.gender = gender;
		this.typeParticipant = typeParticipant;
		this.creatorName = creatorName;
		this.participants = participants;
	}
	
	public EventDTO(Event event, String creatorName, int participants) {
		this.id = event.getId();
		this.name = event.getName();
		this.description = event.getDescription();
		this.address = event.getAddress();
		this.startDate = event.getStartDate();
		this.endDate = event.getEndDate();
		this.creationDate = event.getCreationDate();
		this.type = event.getType();
		this.sport = event.getSport();
		this.minParticipants = event.getMinParticipants();
		this.maxParticipants = event.getMaxParticipants();
		this.price = event.getPrice();
		this.gender = event.getGender();
		this.typeParticipant = event.getTypeParticipant();
		this.creatorName = creatorName;
		this.participants = participants;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
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

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
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

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public int getParticipants() {
		return participants;
	}

	public void setParticipants(int participants) {
		this.participants = participants;
	}
	
}
