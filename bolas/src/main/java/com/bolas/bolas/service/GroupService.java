package com.bolas.bolas.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bolas.bolas.dto.GroupCreationDTO;
import com.bolas.bolas.entity.Group;
import com.bolas.bolas.entity.User;
import com.bolas.bolas.repository.GroupRepository;
import com.bolas.bolas.repository.UserRepository;

@Service
public class GroupService {

	@Autowired
	private GroupRepository groupRepository;
	@Autowired
	private UserRepository userRepository;
	
	public ResponseEntity<Boolean> create(GroupCreationDTO group) {
		Optional<User> user = userRepository.findByEmailAndPassword(group.getSession().getEmail(), group.getSession().getPassword());
		
		if (user.isEmpty()) {
			return new ResponseEntity<Boolean>(false, HttpStatus.NOT_ACCEPTABLE);
		}
		
		Group groupToSave = group.toGroup(user.get());
		
		Group saved = groupRepository.save(groupToSave);
		
		if (saved == null) {
			return new ResponseEntity<Boolean>(false, HttpStatus.CONFLICT);
		}
		
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
}
