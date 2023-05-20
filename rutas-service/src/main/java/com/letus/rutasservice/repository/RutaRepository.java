package com.letus.rutasservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.letus.rutasservice.model.Ruta;

public interface RutaRepository extends JpaRepository<Ruta, Long> {
    Optional<Ruta> findById(int id);
	Boolean existsByid(String id);
}
