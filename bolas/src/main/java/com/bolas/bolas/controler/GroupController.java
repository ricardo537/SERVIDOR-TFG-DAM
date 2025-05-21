package com.bolas.bolas.controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bolas.bolas.dto.FollowDTO;
import com.bolas.bolas.dto.GroupCreationDTO;
import com.bolas.bolas.dto.UserResumeDTO;
import com.bolas.bolas.service.GroupService;
import com.bolas.bolas.service.UserService;

@RestController
@RequestMapping("/bolas/api/group")
public class GroupController {
	
	@Autowired
	private GroupService groupService;
	@Autowired
	private UserService userService;

	@PostMapping("/create")
	public ResponseEntity<Boolean> create(@RequestBody GroupCreationDTO group) {
		return groupService.create(group);
	}
	
	@PostMapping("/follow")
	public ResponseEntity<Boolean> follow(@RequestBody FollowDTO folow) {
		return groupService.follow(folow);
	}
	
	@PostMapping("/stopFollowing")
	public ResponseEntity<Boolean> stopFollowing(@RequestBody FollowDTO follow) {
		return groupService.stopFollow(follow);
	}
	
	@GetMapping("/find/user/{name}")
	public ResponseEntity<List<UserResumeDTO>> findUserByName(@PathVariable String name) {
		return userService.findByName(name);
	}
}
