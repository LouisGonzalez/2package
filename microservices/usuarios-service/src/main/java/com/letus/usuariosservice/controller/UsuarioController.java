package com.letus.usuariosservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.letus.usuariosservice.dto.RecibirDTO;
import com.letus.usuariosservice.dto.UserDTO;
import com.letus.usuariosservice.service.PaqueteService;
import com.letus.usuariosservice.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    PaqueteService paqueteService;

    @Autowired
    UsuarioService usuarioService;
    
    @PostMapping("/create_user")
    public String testRuta(@RequestBody UserDTO userDTO){
        usuarioService.createUser(userDTO);
        return "Created User";
    }

    @GetMapping("/get-users")
    public List<Object[]> getUsers() {
        return usuarioService.getUsers();
    }

    @PostMapping("/recibir-paquete")
    public String moverPaqueteInicio (@RequestBody RecibirDTO recibir){
        paqueteService.receivePaquete(recibir);
        return "Received successfully ...";
    }
}
