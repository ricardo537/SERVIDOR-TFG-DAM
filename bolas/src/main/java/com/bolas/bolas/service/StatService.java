package com.bolas.bolas.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bolas.bolas.dto.StatsDTO;
import com.bolas.bolas.entity.Stats;
import com.bolas.bolas.entity.User;
import com.bolas.bolas.repository.StatsRepository;
import com.bolas.bolas.repository.UserRepository;

@Service
public class StatService {

	@Autowired
	private StatsRepository statsRepository;
	@Autowired
	private UserRepository userRepository;
	
	public ResponseEntity<Boolean> add(StatsDTO statsData) {
		Optional<User> user = userRepository.findByEmailAndPassword(statsData.getSession().getEmail(), statsData.getSession().getPassword());
		
		if (user.isEmpty()) {
			return new ResponseEntity<Boolean>(false, HttpStatus.NOT_ACCEPTABLE);
		}
		
		UUID userId = user.get().getId();
		Optional<Stats> existingStats = statsRepository.findById(userId);
		
		Stats statsToSave;
		if (existingStats.isEmpty()) {
			 statsToSave = statsData.toStats(user.get());
		} else {
			existingStats.get().update(statsData);
			statsToSave = existingStats.get();
		}

	    Stats saved = statsRepository.saveAndFlush(statsToSave);
		
		if (saved == null) {
			return new ResponseEntity<Boolean>(false, HttpStatus.CONFLICT);
		}
		
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
}
