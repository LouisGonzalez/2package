package com.letus.rutasservice.kafka;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.letus.dto.RecSiguienteEvent;
import com.letus.dto.SiguienteEvent;
import com.letus.rutasservice.service.PaqueteService;

@Service
public class SiguienteConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(SiguienteConsumer.class);

    @Autowired
    PaqueteService paqueteService;
    
    private SiguienteProducer siguienteProducer; 
    @KafkaListener(
            topics = "${spring.kafka.topic.name5}"
            ,groupId = "${spring.kafka.consumer.group-id}"
    )
    
    public void consume(RecSiguienteEvent event){
        LOGGER.info(String.format("Order event received in stock service => %s", event.toString()));
        SiguienteEvent siguienteEvent = new SiguienteEvent();
        siguienteEvent.setStatus("PENDING");
        siguienteEvent.setMessage("order status is in pending state");
        siguienteEvent.setPackageId(event.getPackageId());
        siguienteEvent.setNextCheckpoint(paqueteService.getNext(event.getPackageId(),event.getCheckPointId()));
        siguienteProducer.sendMessage(siguienteEvent);
        // save the order event into the database
    }
    public SiguienteConsumer(SiguienteProducer siguienteProducer) {
        this.siguienteProducer = siguienteProducer;
    }
}
