package com.bolas.bolas.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="stats")
public class Stats {
	@Id
	private UUID id;
	
	@Column(nullable = false)
	private Integer[] soccer;
	@Column(nullable = false)
	private Integer[] volleyball;
	@Column(nullable = false)
	private Integer[] basketball;
	@Column(nullable = false)
	private Integer[] tenis;
	
	@OneToOne
	@MapsId
	@JoinColumn(name = "id")
	private User user;
	
	public Stats() {
		super();
		Integer[] defaultValue = {0, 0, 0};
		this.soccer = defaultValue;
		this.volleyball = defaultValue;
		this.basketball = defaultValue;
		this.tenis = defaultValue;
	}

	public Stats(UUID id, Integer[] soccer, Integer[] volleyball, Integer[] basketball, Integer[] tenis) {
		super();
		this.id = id;
		this.soccer = soccer;
		this.volleyball = volleyball;
		this.basketball = basketball;
		this.tenis = tenis;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Integer[] getSoccer() {
		return soccer;
	}

	public void setSoccer(Integer[] soccer) {
		this.soccer = soccer;
	}

	public Integer[] getVolleyball() {
		return volleyball;
	}

	public void setVolleyball(Integer[] volleyball) {
		this.volleyball = volleyball;
	}

	public Integer[] getBasketball() {
		return basketball;
	}

	public void setBasketball(Integer[] basketball) {
		this.basketball = basketball;
	}

	public Integer[] getTenis() {
		return tenis;
	}

	public void setTenis(Integer[] tenis) {
		this.tenis = tenis;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
