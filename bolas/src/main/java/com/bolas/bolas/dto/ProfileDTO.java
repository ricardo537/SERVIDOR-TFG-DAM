package com.bolas.bolas.dto;

import java.util.UUID;

import com.bolas.bolas.entity.Stats;
import com.bolas.bolas.entity.User;

public class ProfileDTO {
	
	private UUID Id;
	private String name;
	private String description;
	private String img;
	private long followers;
	private long follows;
	private StatsDSDTO stats;
	private String gender;
	
	public ProfileDTO(UUID id, String name, String description, String img, long followers, long follows, StatsDSDTO stats, String gender) {
		super();
		Id = id;
		this.name = name;
		this.description = description;
		this.img = img;
		this.followers = followers;
		this.follows = follows;
		this.stats = stats;
		this.gender = gender;
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

	public long getFollowers() {
		return followers;
	}

	public void setFollowers(long followers) {
		this.followers = followers;
	}

	public long getFollows() {
		return follows;
	}

	public void setFollows(long follows) {
		this.follows = follows;
	}

	public StatsDSDTO getStats() {
		return stats;
	}

	public void setStats(StatsDSDTO stats) {
		this.stats = stats;
	}
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public static ProfileDTO toProfile(User user, Stats stats, long followers, long follows) {
		return new ProfileDTO(user.getId(), user.getName(), user.getDescription(), user.getImg(), followers, follows, StatsDSDTO.toStatsDSDTO(stats), user.getGender());
	}
}
