package com.letus.rutasservice.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.letus.dto.EnvioEvent;
import com.letus.rutasservice.model.Paquete;
import com.letus.rutasservice.service.PaqueteService;
import com.letus.rutasservice.service.RutaService;

@Service
public class EnvioConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(EnvioConsumer.class);
    @Autowired
    RutaService rutaService;
    @Autowired
    PaqueteService paqueteService;
    @KafkaListener(
            topics = "${spring.kafka.topic.name}"
            ,groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(EnvioEvent event){
        LOGGER.info(String.format("Order event received in stock service => %s", event.toString()));
        Paquete paquete=paqueteService.inicializePackage(event.getEnvio());
        rutaService.enviarPaquete((long)event.getEnvio().getRutaId(), paquete);
             
        // save the order event into the database
    }
}
