package com.letus.checkpointsservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.letus.checkpointsservice.model.CheckPoint;

@Repository
public interface CheckpointRepository extends JpaRepository<CheckPoint, Long>{
    Optional<CheckPoint> findById(int id);
	Boolean existsByid(String id);

    @Query(value ="SELECT * FROM checkpoint" , nativeQuery = true)
	List<Object[]> findAllCheckPoints();
}   
