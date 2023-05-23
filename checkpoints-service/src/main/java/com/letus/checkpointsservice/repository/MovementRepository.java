package com.letus.checkpointsservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.letus.checkpointsservice.model.Movement;

@Repository
public interface MovementRepository extends JpaRepository<Movement, Long>{
    Optional<Movement> findById(Long id);
	Boolean existsByid(String id);

}   
