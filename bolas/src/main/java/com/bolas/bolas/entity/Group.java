package com.bolas.bolas.entity;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "app_groups")
public class Group {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = true)
	private String img;
	
	@JoinColumn(nullable = false)
	private String creator;
	
	@OneToMany(mappedBy = "group")
	private List<Play> plays;
	
	@OneToMany(mappedBy = "group")
	private List<Participate> participations;

	public Group() {
		super();
	}

	public Group(UUID id, String name, String img, String creator) {
		super();
		this.id = id;
		this.name = name;
		this.img = img;
		this.creator = creator;
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

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public List<Play> getPlays() {
		return plays;
	}

	public void setPlays(List<Play> plays) {
		this.plays = plays;
	}
	
}
