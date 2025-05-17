package com.bolas.bolas.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bolas.bolas.dto.StatsDTO;
import com.bolas.bolas.service.StatService;

@RestController
@RequestMapping("/bolas/api/stats")
public class StatsController {

	@Autowired
	private StatService statService;
	
	@PostMapping("/add")
	public ResponseEntity<Boolean> add(@RequestBody StatsDTO statsData) {
		return statService.add(statsData);
	}
}
