package com.letus.rutasservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.letus.rutasservice.service.RutaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
@RestController
@RequestMapping("/ruta")
public class RutaController {

    @Autowired
    RutaService rutaService;

    @PostMapping("/create_test_ruta")
    public String testRuta(){

        rutaService.createRutaTest();

        return "Created";
    }
}
