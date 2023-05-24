package com.letus.checkpointsservice.kafka;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.letus.checkpointsservice.service.CheckpointService;
import com.letus.dto.SiguienteEvent;

@Service
public class SiguienteConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(SiguienteConsumer.class);
    @Autowired
    CheckpointService checkpointService;

    @KafkaListener(
            topics = "${spring.kafka.topic.name6}"
            ,groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(SiguienteEvent event){
        LOGGER.info(String.format("Order event received in stock service => %s", event.toString()));
        checkpointService.setNext(event);
    }
}
