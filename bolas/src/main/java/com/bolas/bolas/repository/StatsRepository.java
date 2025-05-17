package com.bolas.bolas.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bolas.bolas.entity.Stats;

@Repository
public interface StatsRepository extends JpaRepository<Stats, UUID> {

	Stats save(Stats stats);
	
}
