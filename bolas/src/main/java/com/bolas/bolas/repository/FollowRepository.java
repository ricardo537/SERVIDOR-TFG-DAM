package com.bolas.bolas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bolas.bolas.entity.Follow;
import com.bolas.bolas.entity.FollowId;

@Repository
public interface FollowRepository extends JpaRepository<Follow, FollowId> {

	Follow save(Follow follow);
	
	void delete(Follow follow);
	
}
