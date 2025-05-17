package com.bolas.bolas.dto;

import java.util.UUID;

import com.bolas.bolas.entity.Stats;
import com.bolas.bolas.entity.User;

public class ProfileDTO {
	
	private UUID Id;
	private String name;
	private String description;
	private String img;
	private int followers;
	private int follows;
	private StatsDSDTO stats;
	
	public ProfileDTO(UUID id, String name, String description, String img, int followers, int follows, StatsDSDTO stats) {
		super();
		Id = id;
		this.name = name;
		this.description = description;
		this.img = img;
		this.followers = followers;
		this.follows = follows;
		this.stats = stats;
	}

	public ProfileDTO() {
		super();
	}

	public UUID getId() {
		return Id;
	}

	public void setId(UUID id) {
		Id = id;
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

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public int getFollowers() {
		return followers;
	}

	public void setFollowers(int followers) {
		this.followers = followers;
	}

	public int getFollows() {
		return follows;
	}

	public void setFollows(int follows) {
		this.follows = follows;
	}

	public StatsDSDTO getStats() {
		return stats;
	}

	public void setStats(StatsDSDTO stats) {
		this.stats = stats;
	}
	
	public static ProfileDTO toProfile(User user, Stats stats) {
		return new ProfileDTO(user.getId(), user.getName(), user.getDescription(), user.getImg(), 0, 0, StatsDSDTO.toStatsDSDTO(stats));
	}
}
