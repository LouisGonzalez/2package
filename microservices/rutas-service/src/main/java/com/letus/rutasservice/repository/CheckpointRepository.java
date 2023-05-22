package com.letus.rutasservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.letus.rutasservice.model.CheckPoint;

public interface CheckpointRepository extends JpaRepository<CheckPoint, Long>{
    Optional<CheckPoint> findById(int id);
	Boolean existsByid(String id);
}
