package com.bolas.bolas.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@IdClass(PlayId.class)
@Table(name = "play")
public class Play {

	@Id
	@ManyToOne
	@JoinColumn(name = "member", insertable = false, updatable = false)
	private User member;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "groupId", insertable = false, updatable = false)
	private Group group;

	public Play() {
		super();
	}

	public Play(User member, Group group) {
		super();
		this.member = member;
		this.group = group;
	}

	public User getMember() {
		return member;
	}

	public void setMember(User member) {
		this.member = member;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}
	
}
