package com.letus.rutasservice.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.letus.dto.MoverCheckEvent;
import com.letus.rutasservice.model.Paquete;
import com.letus.rutasservice.service.PaqueteService;
import com.letus.rutasservice.service.RutaService;

@Service
public class MoverCheckConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(MoverCheckConsumer.class);
    @Autowired
    RutaService rutaService;
    @Autowired
    PaqueteService paqueteService;
    @KafkaListener(
            topics = "${spring.kafka.topic.name4}"
            ,groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(MoverCheckEvent event){
        LOGGER.info(String.format("Order event received in stock service => %s", event.toString()));
        paqueteService.movePackageCheckpoint(event.getPackageId(), event.getCheckPointId());
        // Paquete paquete=paqueteService.inicializePackage(event.getEnvio());
        // rutaService.enviarPaquete((long)event.getEnvio().getRutaId(), paquete);
             
        // save the order event into the database
    } 
}
