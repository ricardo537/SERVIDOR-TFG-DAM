package com.bolas.bolas.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "participate")
public class Participate {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	@ManyToOne
    @JoinColumn(name = "group_id", nullable = true)
    private Group group;
	
	@ManyToMany
    @JoinTable(
        name = "participate_user",
        joinColumns = @JoinColumn(name = "participate_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> participants;
	
	@ManyToOne
	@JoinColumn(name = "event")
	private Event event;

	public Participate() {
		super();
		participants = new ArrayList<>();
	}

	public Participate(UUID id, Group group, List<User> participants, Event event) {
		super();
		this.id = id;
		this.group = group;
		this.participants = participants;
		this.event = event;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public List<User> getParticipants() {
		return participants;
	}

	public void setParticipants(List<User> participants) {
		this.participants = participants;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
	
	public void addParticipant(User user) {
		participants.add(user);
	}
	
}
