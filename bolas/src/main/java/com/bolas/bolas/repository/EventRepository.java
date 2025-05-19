package com.bolas.bolas.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bolas.bolas.entity.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID>{

	Event save(Event event);
	
	Optional<Event> findById(UUID id);
}
