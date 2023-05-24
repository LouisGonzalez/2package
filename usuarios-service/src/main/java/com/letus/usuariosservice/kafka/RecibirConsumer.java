package com.letus.usuariosservice.kafka;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.letus.dto.RecibirCheckEvent;
import com.letus.usuariosservice.service.PaqueteService;

@Service
public class RecibirConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(RecibirConsumer.class);
    @Autowired
    PaqueteService paqueteService;  

    @KafkaListener(
            topics = "${spring.kafka.topic.name}"
            ,groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(RecibirCheckEvent event){
        LOGGER.info(String.format("Order event received in stock service => %s", event.toString()));
        paqueteService.createNotification(event);
             
        // save the order event into the database
    }
}
