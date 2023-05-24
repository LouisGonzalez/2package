package com.letus.rutasservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.letus.rutasservice.model.Paquete;

@Repository
public interface PaqueteRepository extends  JpaRepository<Paquete, Long>{
    Optional<Paquete> findById(Long id);
	Boolean existsByid(String id);
}
    

