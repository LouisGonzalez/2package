package com.letus.pagosservice.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.letus.dto.PagoEvent;

@Service
public class PagosProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(PagosProducer.class);

    private NewTopic topic;

    private KafkaTemplate<String, PagoEvent> kafkaTemplate;
    

    public PagosProducer(NewTopic topic, KafkaTemplate<String, PagoEvent> kafkaTemplate){
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(PagoEvent event){
        LOGGER.info(String.format("Pago event => %s", event.toString()));

        // create Message
        Message<PagoEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, topic.name())
                .build();
        kafkaTemplate.send(message);
    }
}
