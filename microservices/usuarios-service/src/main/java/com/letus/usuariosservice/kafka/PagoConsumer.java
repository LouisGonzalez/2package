package com.letus.usuariosservice.kafka;

import org.springframework.stereotype.Service;

import com.letus.dto.PagoEvent;
import com.letus.usuariosservice.service.PaqueteService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

@Service
public class PagoConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(PagoConsumer.class);
    @Autowired
    PaqueteService paqueteService;
    @KafkaListener(
            topics = "${spring.kafka.topic.name2}"
            ,groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(PagoEvent event){
        LOGGER.info(String.format("Order event received in stock service => %s", event.toString()));
        
        paqueteService.createPackage(event);
             
        // save the order event into the database
    }
}
