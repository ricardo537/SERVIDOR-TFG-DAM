package com.bolas.bolas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bolas.bolas.entity.Follow;
import com.bolas.bolas.entity.FollowId;
import com.bolas.bolas.entity.User;

@Repository
public interface FollowRepository extends JpaRepository<Follow, FollowId> {

	Follow save(Follow follow);
	
	void delete(Follow follow);
	
	void deleteByFollowerOrFollows(User follower, User follows);
	
	List<Follow> findByFollows(User follows);
	
	List<Follow> findByFollower(User follower);
	
	long countByFollower(User follower);
	
	long countByFollows(User follows);
	
}
