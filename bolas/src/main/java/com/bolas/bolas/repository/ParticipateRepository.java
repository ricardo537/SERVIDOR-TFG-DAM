package com.bolas.bolas.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bolas.bolas.entity.Event;
import com.bolas.bolas.entity.Group;
import com.bolas.bolas.entity.Participate;

@Repository
public interface ParticipateRepository extends JpaRepository<Participate, UUID> {

	Participate save(Participate participate);
	
	Optional<Participate> findById(UUID id);
	
	Optional<Participate> findByGroupAndEvent(Group group, Event event);
	
	List<Participate> findByEvent(Event event);
	
	void deleteByEvent(Event event);
}
