package com.bolas.bolas.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bolas.bolas.dto.FollowDTO;
import com.bolas.bolas.dto.GroupCreationDTO;
import com.bolas.bolas.entity.Follow;
import com.bolas.bolas.entity.FollowId;
import com.bolas.bolas.entity.Group;
import com.bolas.bolas.entity.User;
import com.bolas.bolas.repository.FollowRepository;
import com.bolas.bolas.repository.GroupRepository;
import com.bolas.bolas.repository.UserRepository;

@Service
public class GroupService {

	@Autowired
	private GroupRepository groupRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private FollowRepository followRepository;
	
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
	
	public ResponseEntity<Boolean> follow(FollowDTO follow) {
		Optional<User> follower = userRepository.findByEmailAndPassword(follow.getSession().getEmail(), follow.getSession().getPassword());
		Optional<User> follows = userRepository.findById(follow.getUserId());
		
		if (follower.isEmpty() || follows.isEmpty()) {
			return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);	
		}
		
		Follow followToSaved = new Follow(follower.get(), follows.get());
		Follow saved = followRepository.save(followToSaved);
		
		if (saved == null) {
			return new ResponseEntity<Boolean>(false, HttpStatus.CONFLICT);
		}
		
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	public ResponseEntity<Boolean> stopFollow(FollowDTO follow) {
		Optional<User> follower = userRepository.findByEmailAndPassword(follow.getSession().getEmail(), follow.getSession().getPassword());
		Optional<User> follows = userRepository.findById(follow.getUserId());
		
		if (follower.isEmpty() || follows.isEmpty()) {
			return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);	
		}
		
		FollowId id = new FollowId(follower.get().getId(), follows.get().getId());
		Optional<Follow> find = followRepository.findById(id);
		
		if (find.isEmpty()) {
			return new ResponseEntity<Boolean>(false, HttpStatus.CONFLICT);
		}
		
		followRepository.delete(find.get());
		
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
}
