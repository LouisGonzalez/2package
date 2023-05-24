package com.letus.usuariosservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.letus.usuariosservice.model.Notificacion;


@Repository
public interface NotificacionRepository extends  JpaRepository<Notificacion, Long>{
    Optional<Notificacion> findById(Long id);
	Boolean existsByid(Long id);
}
