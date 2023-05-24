package com.letus.usuariosservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.letus.dto.RecibirCheckEvent;
import com.letus.usuariosservice.dto.UserDTO;
import com.letus.usuariosservice.model.Paquete;
import com.letus.usuariosservice.model.Usuario;
import com.letus.usuariosservice.repository.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    public void createUser(UserDTO user){
        Usuario usuario = new Usuario(user.getName(), user.getLastName(), 
        user.getPhoneNumber(), user.getAddress());

        usuarioRepository.save(usuario);
    }

    
}
