package com.bolas.bolas.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bolas.bolas.entity.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, UUID> {
	
	Group save(Group group);
	
	Optional<Group> findById(UUID id);
	
}
