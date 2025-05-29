package com.bolas.bolas.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bolas.bolas.dto.AddMemberDTO;
import com.bolas.bolas.dto.ExitGroupDTO;
import com.bolas.bolas.dto.FollowDTO;
import com.bolas.bolas.dto.GroupCreationDTO;
import com.bolas.bolas.dto.GroupResumeDTO;
import com.bolas.bolas.dto.SessionDTO;
import com.bolas.bolas.dto.UpdateGroupDTO;
import com.bolas.bolas.dto.UserResumeDTO;
import com.bolas.bolas.entity.Follow;
import com.bolas.bolas.entity.FollowId;
import com.bolas.bolas.entity.Group;
import com.bolas.bolas.entity.Play;
import com.bolas.bolas.entity.PlayId;
import com.bolas.bolas.entity.User;
import com.bolas.bolas.repository.FollowRepository;
import com.bolas.bolas.repository.GroupRepository;
import com.bolas.bolas.repository.PlayRepository;
import com.bolas.bolas.repository.UserRepository;

@Service
public class GroupService {

	@Autowired
	private GroupRepository groupRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private FollowRepository followRepository;
	@Autowired
	private PlayRepository playRepository;
	
	public ResponseEntity<Boolean> create(GroupCreationDTO group) {
		Optional<User> user = userRepository.findByEmailAndPassword(group.getSession().getEmail(), group.getSession().getPassword());
		
		if (user.isEmpty()) {
			return new ResponseEntity<Boolean>(false, HttpStatus.NOT_ACCEPTABLE);
		}
		
		Group groupToSave = group.toGroup(user.get());
		
		Group saved = groupRepository.save(groupToSave);
		
		Play savedUser = playRepository.save(new Play(user.get(), saved));
		
		if (saved == null || savedUser == null) {
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
		
		if (follower.get().equals(follows.get())) {
			return new ResponseEntity<Boolean>(false, HttpStatus.CONFLICT);
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
	
	public ResponseEntity<List<UserResumeDTO>> getFollowers(UUID id) {
		Optional<User> user = userRepository.findById(id);
		
		if (user.isEmpty()) {
			return new ResponseEntity<List<UserResumeDTO>>(List.of(), HttpStatus.NOT_FOUND);
		}
		
		List<Follow> follows = followRepository.findByFollows(user.get());
		List<UserResumeDTO> followers = follows.stream().map(follow -> new UserResumeDTO(follow.getFollower())).collect(Collectors.toList());
		
		return new ResponseEntity<List<UserResumeDTO>>(followers, HttpStatus.OK);
	}
	
	public ResponseEntity<List<UserResumeDTO>> getFollows(UUID id) {
		Optional<User> user = userRepository.findById(id);
		
		if (user.isEmpty()) {
			return new ResponseEntity<List<UserResumeDTO>>(List.of(), HttpStatus.NOT_FOUND);
		}
		
		List<Follow> followList = followRepository.findByFollower(user.get());
		List<UserResumeDTO> follows = followList.stream().map(follow -> new UserResumeDTO(follow.getFollows())).collect(Collectors.toList());
		
		return new ResponseEntity<List<UserResumeDTO>>(follows, HttpStatus.OK);
	}
	
	public ResponseEntity<Boolean> addMember(AddMemberDTO addMember) {
		Optional<User> user = userRepository.findByEmailAndPassword(addMember.getSession().getEmail(), addMember.getSession().getPassword());
		Optional<User> member = userRepository.findById(addMember.getMember());
		Optional<Group> group = groupRepository.findById(addMember.getGroup());
		
		if (user.isEmpty() || member.isEmpty() || group.isEmpty()) {
			return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
		}
		
		Play play = playRepository.save(new Play(member.get(), group.get()));
		
		if (play == null) {
			return new ResponseEntity<Boolean>(false, HttpStatus.CONFLICT);
		}
		
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	public ResponseEntity<List<GroupResumeDTO>> getMyGroups(SessionDTO session) {
		Optional<User> user = userRepository.findByEmailAndPassword(session.getEmail(), session.getPassword());
		
		if (user.isEmpty()) {
			return new ResponseEntity<List<GroupResumeDTO>>(List.of(), HttpStatus.NOT_ACCEPTABLE);
		}
		
		List<Play> groups = playRepository.findByMember(user.get());
		List<GroupResumeDTO> results = groups.stream().map(group -> new GroupResumeDTO(group.getGroup())).collect(Collectors.toList());
		
		return new ResponseEntity<List<GroupResumeDTO>>(results, HttpStatus.OK);
	}
	
	public ResponseEntity<Boolean> update(UpdateGroupDTO update) {
		Optional<Group> group = groupRepository.findById(update.getGroup());
		
		if (group.isEmpty()) {
			return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
		}
		
		Group updated = update.update(group.get());
		
		groupRepository.save(updated);
		
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	public boolean updateImgGroup(String img, UUID id) {
		Optional<Group> group = groupRepository.findById(id);
		
		if (group.isEmpty()) {
			return false;
		}
		
		Group groupUpdated = group.get();
		groupUpdated.setImg(img);
		Group saved = groupRepository.save(groupUpdated);
		
		if (saved == null) {
			return false;
		}
		
		return true;
	}
	
	public ResponseEntity<Boolean> exit(ExitGroupDTO exit) {
		Optional<Group> group = groupRepository.findById(exit.getGroup());
		Optional<User> user = userRepository.findByEmailAndPassword(exit.getSession().getEmail(), exit.getSession().getPassword());
		
		if(group.isEmpty() || user.isEmpty()) {
			return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
		}
		
		Optional<Play> play = playRepository.findById(new PlayId(user.get().getId(), group.get().getId()));
		
		if (play.isEmpty()) {
			return new ResponseEntity<Boolean>(false, HttpStatus.CONFLICT);
		}
		
		playRepository.delete(play.get());
		
		List<Play> plays = playRepository.findByGroup(group.get());
		
		if (plays.isEmpty()) {
			groupRepository.delete(group.get());
		}
		
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	public ResponseEntity<List<UserResumeDTO>> getMembers(UUID id) {
		Optional<Group> group = groupRepository.findById(id);
		
		if (group.isEmpty()) {
			return new ResponseEntity<List<UserResumeDTO>>(List.of(), HttpStatus.NOT_FOUND);
		}
		
		List<Play> plays = playRepository.findByGroup(group.get());
		List<UserResumeDTO> members = plays.stream().map(play -> new UserResumeDTO(play.getMember())).collect(Collectors.toList());
		
		return new ResponseEntity<List<UserResumeDTO>>(members, HttpStatus.OK);
	}
	
	public ResponseEntity<GroupResumeDTO> getGroup(UUID id) {
		Optional<Group> group = groupRepository.findById(id);
		
		if (group.isEmpty()) {
			return new ResponseEntity<GroupResumeDTO>(new GroupResumeDTO(), HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<GroupResumeDTO>(new GroupResumeDTO(group.get()), HttpStatus.OK);
	}
}
