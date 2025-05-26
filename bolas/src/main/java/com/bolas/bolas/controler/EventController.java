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
import com.bolas.bolas.dto.EventPublishDTO;
import com.bolas.bolas.dto.FilterEventDTO;
import com.bolas.bolas.dto.JoinEventDTO;
import com.bolas.bolas.dto.JoinLinkDTO;
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
}
