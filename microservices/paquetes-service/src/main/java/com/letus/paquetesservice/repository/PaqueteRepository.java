package com.letus.paquetesservice.repository;

import org.springframework.stereotype.Repository;

import com.letus.paquetesservice.model.Paquete;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PaqueteRepository extends  JpaRepository<Paquete, Long>{
    Optional<Paquete> findById(int id);
	Boolean existsByid(String id);
}
