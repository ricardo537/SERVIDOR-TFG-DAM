package com.bolas.bolas.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bolas.bolas.entity.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID>{

	Event save(Event event);
	
	Optional<Event> findById(UUID id);
	
	Page<Event> findByStartDateAfter(LocalDateTime startDate, Pageable pageable);
}
