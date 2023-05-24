package com.letus.usuariosservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.letus.usuariosservice.model.Usuario;

@Repository
public interface UsuarioRepository extends  JpaRepository<Usuario, Long>{
    Optional<Usuario> findById(Long id);
	Boolean existsByid(Long id);
}
