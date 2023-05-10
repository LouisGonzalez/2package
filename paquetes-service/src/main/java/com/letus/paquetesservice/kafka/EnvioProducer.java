package com.letus.paquetesservice.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.letus.dto.EnvioEvent;


@Service
public class EnvioProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(EnvioProducer.class);

    private NewTopic topic;

    private KafkaTemplate<String, EnvioEvent> kafkaTemplate;
    

    public EnvioProducer(NewTopic topic, KafkaTemplate<String, EnvioEvent> kafkaTemplate){
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(EnvioEvent event){
        LOGGER.info(String.format("Order event => %s", event.toString()));

        // create Message
        Message<EnvioEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, topic.name())
                .build();
        kafkaTemplate.send(message);
    }

}
