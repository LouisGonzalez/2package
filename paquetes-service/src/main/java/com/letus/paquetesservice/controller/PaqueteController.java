package com.letus.paquetesservice.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.letus.dto.Envio;
import com.letus.dto.EnvioEvent;
import com.letus.paquetesservice.kafka.EnvioProducer;
import com.letus.paquetesservice.model.Paquete;
import com.letus.paquetesservice.service.PaqueteService;

@RestController
@RequestMapping("/paquete")
public class PaqueteController {

    private EnvioProducer envioProducer;

    @Autowired
    PaqueteService paqueteService;

    public PaqueteController(EnvioProducer envioProducer) {
        this.envioProducer = envioProducer;
    }

    

    @PostMapping("/enviar")
    public String placeOrder(@RequestBody Envio envio){

        envio.setId(UUID.randomUUID().toString());

        EnvioEvent envioEvent = new EnvioEvent();
        paqueteService.enviarPackage(envio);
        envioEvent.setStatus("PENDING");
        envioEvent.setMessage("order status is in pending state");
        envioEvent.setEnvio(envio);

        envioProducer.sendMessage(envioEvent);

        return "Order placed successfully ...";
    }

    @PostMapping("/create_test_package")
    public String placeOrder(){

        paqueteService.createPackage();

        return "Created";
    }



    @GetMapping("/hola")
    public String prueba(){
        return "mundo";
    }

    @GetMapping("/package-list")
    public List<Paquete> getDevs() {
        return paqueteService.getPackages();
    }
}
