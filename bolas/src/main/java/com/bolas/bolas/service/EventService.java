package com.bolas.bolas.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bolas.bolas.dto.EventDTO;
import com.bolas.bolas.dto.EventPublishDTO;
import com.bolas.bolas.entity.Event;
import com.bolas.bolas.entity.User;
import com.bolas.bolas.repository.EventRepository;
import com.bolas.bolas.repository.UserRepository;

@Service
public class EventService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired EventRepository eventRepository;
	
	public ResponseEntity<Boolean> publish(EventPublishDTO eventData) {
		LocalDateTime now = LocalDateTime.now();
		if (eventData.getStartDate().isAfter(now) && eventData.getEndDate().isAfter(eventData.getStartDate())) {
			Optional<User> user = userRepository.findByEmailAndPassword(eventData.getSession().getEmail(), eventData.getSession().getPassword());
			
			if (user.isPresent()) {
				Event event = new Event(eventData.getName(), eventData.getDescription(), eventData.getDescription(), eventData.getStartDate(), eventData.getEndDate(),
						now, eventData.getType(), eventData.getSport(), eventData.getMinParticipants(), eventData.getMaxParticipants(),
						eventData.getPrice(), eventData.getGender(), eventData.getTypeParticipant(), user.get());
				
				eventRepository.save(event);
				
				return new ResponseEntity<Boolean>(true, HttpStatus.OK);
			}
		}
		
		return new ResponseEntity<Boolean>(false, HttpStatus.NOT_ACCEPTABLE);
	}
}
