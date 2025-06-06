package com.bolas.bolas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bolas.bolas.entity.Group;
import com.bolas.bolas.entity.Play;
import com.bolas.bolas.entity.PlayId;
import com.bolas.bolas.entity.User;

@Repository
public interface PlayRepository extends JpaRepository<Play, PlayId>{

	Play save(Play play);
	
	List<Play> findByMember(User member);
	
	List<Play> findByGroup(Group group);
	
	Optional<Play> findById(PlayId id);
	
	void delete(Play play);
}
