package com.bolas.bolas.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bolas.bolas.entity.User;

@Repository
public interface UserRepository extends JpaRepository <User, UUID>{
	public User save(User user);
	
}
