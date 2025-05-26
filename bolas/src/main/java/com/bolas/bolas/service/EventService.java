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
import com.bolas.bolas.dto.JoinLinkDTO;
import com.bolas.bolas.dto.JoinTeamDTO;
import com.bolas.bolas.dto.UnjoinEventDTO;
import com.bolas.bolas.entity.Event;
import com.bolas.bolas.entity.Group;
import com.bolas.bolas.entity.Participate;
import com.bolas.bolas.entity.User;
import com.bolas.bolas.repository.EventRepository;
import com.bolas.bolas.repository.GroupRepository;
import com.bolas.bolas.repository.ParticipateRepository;
import com.bolas.bolas.repository.UserRepository;

@Service
public class EventService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired 
	private EventRepository eventRepository;
	
	@Autowired
	private GroupRepository groupRepository;
	
	@Autowired
	private ParticipateRepository participateRepository;
	
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
	
	public ResponseEntity<Boolean> join(JoinEventDTO join) {
		Optional<User> user = userRepository.findByEmailAndPassword(join.getSession().getEmail(), join.getSession().getPassword());
		Optional<Event> event = eventRepository.findById(join.getEvent());
		
		
		if (user.isEmpty() || event.isEmpty()) {
			return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
		}
		
		Participate participate = new Participate();
		
		participate.addParticipant(user.get());
		participate.setEvent(event.get());
		
		if (join.getGroup() != null) {
			Optional<Group> group = groupRepository.findById(join.getGroup());
			if (group.isPresent()) {
				participate.setGroup(group.get());
			} else {
				return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
			}
		}
		
		List<Participate> participations = participateRepository.findByEvent(event.get());
		
		List<Boolean> isJoined = participations.stream().map(p -> {
			 return p.getParticipants().contains(user.get());
		}).filter(pa -> pa).collect(Collectors.toList());
		
		if (isJoined.size() >= 1) {
			return new ResponseEntity<Boolean>(false, HttpStatus.CONFLICT);
		}
		
		participateRepository.save(participate);
		
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	//Apuntarse dentro de un equipo
	public ResponseEntity<Boolean> joinEventInTeam(JoinTeamDTO join) {
		Optional<Event> event = eventRepository.findById(join.getEvent());
		Optional<User> user = userRepository.findByEmailAndPassword(join.getSession().getEmail(), join.getSession().getPassword());
		Optional<Group> group = groupRepository.findById(join.getGroup());
		
		if (event.isEmpty() || user.isEmpty() || group.isEmpty()) {
			return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
		}
		
		Optional<Participate> participate = participateRepository.findByGroupAndEvent(group.get(), event.get());
		
		if (participate.isEmpty()) {
			return new ResponseEntity<Boolean>(false, HttpStatus.CONFLICT);
		}
		
		Participate participated = participate.get();
		participated.addParticipant(user.get());
		participateRepository.save(participated);
		
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	//Desapuntarse de un evento
	public ResponseEntity<Boolean> unjoinEvent(UnjoinEventDTO unjoin) {
		Optional<Event> event = eventRepository.findById(unjoin.getEvent());
		Optional<User> user = userRepository.findByEmailAndPassword(unjoin.getSession().getEmail(), unjoin.getSession().getPassword());
		
		if (event.isEmpty() || user.isEmpty()) {
			return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
		}
		
		List<Participate> participations = participateRepository.findByEvent(event.get());
		participations.stream().forEach(participation -> {
			participation.subtractParticipant(user.get());
			if (participation.getParticipants().size() == 0) {
				participateRepository.delete(participation);
			} else {
				participateRepository.save(participation);
			}
		});
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
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
					String name;
					if (event.getUser() == null) {
						name = "El usuario ya no existe";
					} else {
						name = event.getUser().getName();
					}
					List<Participate> participations = participateRepository.findByEvent(event);
					List<Boolean> isJoined = participations.stream().map(p -> {
						 return p.getParticipants().contains(user.get());
					}).filter(pa -> pa == true).collect(Collectors.toList());
					
					if (isJoined.size() == 0 && isValidEvent(event, user.get(), filter)) {
						return new EventDTO(event, name, participations.size());
					}
					return null;
				}).filter(e -> e != null)
				.collect(Collectors.toList());
		
		return new ResponseEntity<List<EventDTO>>(eventsDTO, HttpStatus.OK);
	}
	
	public boolean isValidEvent(Event event, User user, FilterEventDTO filter) {
		if (event.getGender().equals(user.getGender()) || event.getGender().equals("mixto")) {
			List<Participate> participations = participateRepository.findByEvent(event);
			if (participations.size() < event.getMaxParticipants()) {
				if (!participations.contains(user)) {
					if ((event.getGender().equals(filter.getGender()) || filter.getGender().equals("")) && (filter.getSport().equals(event.getSport()) || filter.getSport().equals("")) &&
							(filter.getType().equals(event.getType()) || filter.getType().equals("")) && (filter.getTypeParticipant().equals(event.getTypeParticipant()) || filter.getTypeParticipant().equals(""))) {
						return true;
					}
				}
			}
		}
		
		return false;
	}
}
