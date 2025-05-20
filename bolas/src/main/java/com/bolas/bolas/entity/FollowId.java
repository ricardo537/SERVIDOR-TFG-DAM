package com.bolas.bolas.entity;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class FollowId implements Serializable {

	private UUID follower;
    private UUID follows;
    
	public FollowId() {
		super();
	}

	public FollowId(UUID follower, UUID follows) {
		super();
		this.follower = follower;
		this.follows = follows;
	}

	public UUID getfollower() {
		return follower;
	}

	public void setfollower(UUID follower) {
		this.follower = follower;
	}

	public UUID getfollows() {
		return follows;
	}

	public void setfollows(UUID follows) {
		this.follows = follows;
	}
    
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FollowId)) return false;
        FollowId that = (FollowId) o;
        return Objects.equals(follower, that.follower) &&
               Objects.equals(follows, that.follows);
    }

    @Override
    public int hashCode() {
        return Objects.hash(follower, follows);
    }
}
