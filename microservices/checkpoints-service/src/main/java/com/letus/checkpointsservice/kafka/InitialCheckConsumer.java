package com.letus.checkpointsservice.kafka;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.letus.checkpointsservice.service.CheckpointService;
import com.letus.dto.MoverEvent;


@Service

public class InitialCheckConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(CheckpointConsumer.class);
    @Autowired
    CheckpointService checkpointService;

    @KafkaListener(
            topics = "${spring.kafka.topic.name2}"
            ,groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(MoverEvent event){
        LOGGER.info(String.format("Order event received in stock service => %s", event.toString()));
        checkpointService.initializePaquete(event);
    }
}
