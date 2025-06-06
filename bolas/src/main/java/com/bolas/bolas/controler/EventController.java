package com.bolas.bolas.controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bolas.bolas.dto.EventDTO;
import com.bolas.bolas.dto.EventGroupDTO;
import com.bolas.bolas.dto.EventPublishDTO;
import com.bolas.bolas.dto.FilterEventDTO;
import com.bolas.bolas.dto.GetGroupEventDTO;
import com.bolas.bolas.dto.IdDTO;
import com.bolas.bolas.dto.JoinEventDTO;
import com.bolas.bolas.dto.JoinLinkDTO;
import com.bolas.bolas.dto.JoinTeamDTO;
import com.bolas.bolas.dto.SessionDTO;
import com.bolas.bolas.dto.UnjoinEventDTO;
import com.bolas.bolas.repository.EventRepository;
import com.bolas.bolas.service.EventService;

@RestController
@RequestMapping("/bolas/api/event")
public class EventController {

	@Autowired
	private EventService eventService;
	
	@PostMapping("/publish")
	public ResponseEntity<Boolean> publish(@RequestBody EventPublishDTO eventData) {
		return eventService.publish(eventData);
	}
	
	@PostMapping("/getFilteredEvents")
	public ResponseEntity<List<EventDTO>> getFilteredEvents(@RequestBody FilterEventDTO filter, @RequestParam int page, @RequestParam int size) {
		return eventService.getFilteredEvents(filter, page, size);
	}
	
	@PostMapping("/join")
	public ResponseEntity<Boolean> join(@RequestBody JoinEventDTO join) {
		return eventService.join(join);
	}
	
	@PostMapping("/unjoin")
	public ResponseEntity<Boolean> unjoin(@RequestBody UnjoinEventDTO unjoin) {
		return eventService.unjoinEvent(unjoin);
	}
	
	@PostMapping("/joinTeam")
	public ResponseEntity<Boolean> joinInTeam(@RequestBody JoinTeamDTO join) {
		return eventService.joinEventInTeam(join);
	}
	
	@PostMapping("/delete")
	public ResponseEntity<Boolean> delete(@RequestBody IdDTO id) {
		return eventService.deleteEvent(id.getId());
	}
	
	@PostMapping("/getMyCreatedEvents")
	public ResponseEntity<List<EventDTO>> getMyCreatedEvents(@RequestBody SessionDTO session) {
		return eventService.getMyCreatedEvents(session);
	}
	
	@PostMapping("/getEventsIJoin")
	public ResponseEntity<List<EventDTO>> getEventsIJoin(@RequestBody SessionDTO session) {
		return eventService.getEventsIJoin(session);
	}
	
	@PostMapping("/getEventsOfGroup")
	public ResponseEntity<List<EventGroupDTO>> getEventsOfGroup(@RequestBody GetGroupEventDTO g) {
		return eventService.getEventsOfGroup(g);
	}
}
