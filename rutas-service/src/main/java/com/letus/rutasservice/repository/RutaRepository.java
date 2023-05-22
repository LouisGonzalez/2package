package com.letus.rutasservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.letus.rutasservice.model.Ruta;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface RutaRepository extends JpaRepository<Ruta, Long> {
    Optional<Ruta> findById(int id);
	Boolean existsByid(String id);

    @Query(value ="SELECT * FROM ruta" , nativeQuery = true)
	List<Object[]> findAllRutas();
}
