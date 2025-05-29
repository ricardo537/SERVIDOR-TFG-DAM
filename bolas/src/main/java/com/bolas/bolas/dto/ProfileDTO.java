package com.bolas.bolas.dto;

import java.util.UUID;

import com.bolas.bolas.entity.Stats;
import com.bolas.bolas.entity.User;

public class ProfileDTO {
	
	private UUID id;
	private String name;
	private String description;
	private String img;
	private long followers;
	private long follows;
	private StatsDSDTO stats;
	private String gender;
	private boolean follow;
	
	public ProfileDTO(UUID id, String name, String description, String img, long followers, long follows, StatsDSDTO stats, String gender, boolean follow) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.img = img;
		this.followers = followers;
		this.follows = follows;
		this.stats = stats;
		this.gender = gender;
		this.follow = follow;
	}

	public ProfileDTO() {
		super();
	}

	public UUID getId() {
		return this.id;
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

	public boolean isFollow() {
		return follow;
	}

	public void setFollow(boolean follow) {
		this.follow = follow;
	}

	public static ProfileDTO toProfile(User user, Stats stats, long followers, long follows, boolean follow) {
		return new ProfileDTO(user.getId(), user.getName(), user.getDescription(), user.getImg(), followers, follows, StatsDSDTO.toStatsDSDTO(stats), user.getGender(), follow);
	}
}
