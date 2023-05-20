package com.letus.pagosservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.letus.pagosservice.model.Pago;

@Repository
public interface PagosRepository  extends JpaRepository<Pago, Long>{
    Optional<Pago> findById(int id);
	Boolean existsByid(String id);
}
