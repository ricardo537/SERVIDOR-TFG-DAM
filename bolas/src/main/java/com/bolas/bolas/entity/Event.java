package com.bolas.bolas.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "events")
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String description;
	
	@Column(nullable = false)
	private String address;
	
	@Column(nullable = false)
	private LocalDateTime startDate;
	
	@Column(nullable = false)
	private LocalDateTime endDate;
	
	@Column(nullable = false) 
	private LocalDateTime creationDate;
	
	@Column(nullable = false)
	private String type;
	
	@Column(nullable = false)
	private String sport;
	
	@Column(nullable = false)
	private Integer minParticipants;
	
	@Column(nullable = false)
	private Integer maxParticipants;
	
	@Column(nullable = false)
	private Double price;
	
	@Column(nullable = false)
	private String gender;
	
	@Column(nullable = false)
	private String typeParticipant;
	
	@ManyToOne
	@JoinColumn(name = "creatorId", nullable = true)
	private User user;

	public Event() {
		super();
	}

	public Event(UUID id, String name, String description, String address, LocalDateTime startDate,
			LocalDateTime endDate, LocalDateTime creationDate, String type, String sport, Integer minParticipants,
			Integer maxParticipants, Double price, String gender, String typeParticipant, User user) {
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
		this.user = user;
	}

	public Event(String name, String description, String address, LocalDateTime startDate, LocalDateTime endDate,
			LocalDateTime creationDate, String type, String sport, Integer minParticipants, Integer maxParticipants,
			Double price, String gender, String typeParticipant, User user) {
		super();
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
		this.user = user;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
