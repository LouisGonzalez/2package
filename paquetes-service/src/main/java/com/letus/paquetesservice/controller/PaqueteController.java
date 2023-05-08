package com.letus.paquetesservice.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.letus.paquetesservice.dto.Envio;
import com.letus.paquetesservice.dto.EnvioEvent;
import com.letus.paquetesservice.kafka.EnvioProducer;

@RestController
@RequestMapping("/paquete")
public class PaqueteController {

    private EnvioProducer envioProducer;

    public PaqueteController(EnvioProducer envioProducer) {
        this.envioProducer = envioProducer;
    }

    @PostMapping("/enviar")
    public String placeOrder(@RequestBody Envio envio){

        envio.setId(UUID.randomUUID().toString());

        EnvioEvent envioEvent = new EnvioEvent();
        envioEvent.setStatus("PENDING");
        envioEvent.setMessage("order status is in pending state");
        envioEvent.setEnvio(envio);

        envioProducer.sendMessage(envioEvent);

        return "Order placed successfully ...";
    }

    // @PostMapping("/eviar")
    // public ResponseEntity<?> enviarARuta(@RequestBody Envio env){
    //     return null;
    // }

    // @GetMapping("/hola")
    // public String prueba(){
    //     return "mundo";
    // }
}
