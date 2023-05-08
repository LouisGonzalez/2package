package com.letus.rutasservice.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.letus.rutasservice.dto.EnvioEvent;

@Service
public class EnvioConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(EnvioConsumer.class);

    @KafkaListener(
            topics = "${spring.kafka.topic.name}"
            ,groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(EnvioEvent event){
        LOGGER.info(String.format("Order event received in stock service => %s", event.toString()));

        // save the order event into the database
    }
}
