package com.bolas.bolas.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bolas.bolas.dto.EventDTO;
import com.bolas.bolas.dto.EventPublishDTO;
import com.bolas.bolas.dto.FilterEventDTO;
import com.bolas.bolas.dto.JoinEventDTO;
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
	
	//FALTA TERMINARLO
	public ResponseEntity<Boolean> join(JoinEventDTO join) {
		return null;
	}
	
	public ResponseEntity<List<EventDTO>> getFilteredEvents(FilterEventDTO filter, int page, int size) {
		Optional<User> user = userRepository.findByEmailAndPassword(filter.getSession().getEmail(), filter.getSession().getPassword());
		if (user.isEmpty()) {
			return new ResponseEntity<List<EventDTO>>(List.of(), HttpStatus.NOT_ACCEPTABLE);
		}
		
		LocalDateTime now = LocalDateTime.now();
		if (filter.getStartDate() == null || filter.getStartDate().isBefore(now)) {
			filter.setStartDate(now);
		}
		
		List<Event> events = eventRepository.findByStartDateAfter(filter.getStartDate(), PageRequest.of(page, size)).getContent();
		List<EventDTO> eventsDTO = events.stream()
				.map((event) -> {
					//Falta comprobar si hay plazas
					String name;
					if (event.getUser() == null) {
						name = "El usuario ya no existe";
					} else {
						name = event.getUser().getName();
					}
					if (isValidEvent(event, user.get(), filter)) {
						return new EventDTO(event, name,0);
					}
					return null;
				}).filter(e -> e != null)
				.collect(Collectors.toList());
		
		return new ResponseEntity<List<EventDTO>>(eventsDTO, HttpStatus.OK);
	}
	
	public boolean isValidEvent(Event event, User user, FilterEventDTO filter) {
		if (event.getGender().equals(user.getGender()) || event.getGender().equals("mixto")) {
			//Falta comprobar que no esté lleno
			if ((event.getGender().equals(filter.getGender()) || filter.getGender().equals("")) && (filter.getSport().equals(event.getSport()) || filter.getSport().equals("")) &&
					(filter.getType().equals(event.getType()) || filter.getType().equals("")) && (filter.getTypeParticipant().equals(event.getTypeParticipant()) || filter.getTypeParticipant().equals(""))) {
				return true;
			}
		}
		
		return false;
	}
}
