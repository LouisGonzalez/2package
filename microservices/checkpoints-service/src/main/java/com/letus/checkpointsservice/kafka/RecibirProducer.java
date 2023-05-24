package com.letus.checkpointsservice.kafka;

import org.springframework.stereotype.Service;

import com.letus.dto.RecibirCheckEvent;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

@Service
public class RecibirProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(RecibirProducer.class);

    private NewTopic topic;
    @Value("${spring.kafka.topic.name4}")
    private String topicName;
    @Autowired
    @Qualifier("recibir")
    private KafkaTemplate<String, RecibirCheckEvent> kafkaTemplate;


    public RecibirProducer(NewTopic topic, KafkaTemplate<String, RecibirCheckEvent> kafkaTemplate){
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(RecibirCheckEvent event){
        LOGGER.info(String.format("Order event => %s", event.toString()));

        // create Message
        Message<RecibirCheckEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, topicName)
                .build();
        kafkaTemplate.send(message);
    }    
}
