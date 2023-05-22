package com.letus.paquetesservice.repository;

import org.springframework.stereotype.Repository;

import com.letus.paquetesservice.model.Paquete;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface PaqueteRepository extends  JpaRepository<Paquete, Long>{
    Optional<Paquete> findById(int id);
	Boolean existsByid(String id);

    @Query(value ="SELECT * FROM paquetes" , nativeQuery = true)
	List<Object[]> findAllPaquetes();
}
