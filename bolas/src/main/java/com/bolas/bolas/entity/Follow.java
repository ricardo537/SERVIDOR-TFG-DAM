package com.bolas.bolas.entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@IdClass(FollowId.class)
@Table(name = "follow")
public class Follow {

	@Id
	@ManyToOne
	@JoinColumn(name = "followerId", insertable = false, updatable = false)
	private User follower;

	@Id
	@ManyToOne
	@JoinColumn(name = "followsId", insertable = false, updatable = false)
	private User follows;


	public Follow() {
		super();
	}

	public Follow(User follower, User follows) {
		super();
		this.follower = follower;
		this.follows = follows;
	}


	public User getFollower() {
		return follower;
	}

	public void setFollower(User follower) {
		this.follower = follower;
	}

	public User getFollows() {
		return follows;
	}

	public void setFollows(User follows) {
		this.follows = follows;
	}
	
}
