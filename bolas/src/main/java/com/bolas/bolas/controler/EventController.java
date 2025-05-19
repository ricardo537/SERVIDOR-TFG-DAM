package com.bolas.bolas.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bolas.bolas.dto.EventDTO;
import com.bolas.bolas.dto.EventPublishDTO;
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
}
