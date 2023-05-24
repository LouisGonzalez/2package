package com.letus.checkpointsservice.kafka;
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
import org.springframework.stereotype.Service;

import com.letus.dto.RecSiguienteEvent;
import com.letus.dto.RecibirCheckEvent;

@Service
public class SigueinteProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(SigueinteProducer.class);

    private NewTopic topic;
    @Value("${spring.kafka.topic.name5}")
    private String topicName;
    @Autowired
    @Qualifier("siguiente")
    private KafkaTemplate<String, RecSiguienteEvent> kafkaTemplate;
    

    public SigueinteProducer(NewTopic topic, KafkaTemplate<String, RecSiguienteEvent> kafkaTemplate){
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(RecSiguienteEvent event){
        LOGGER.info(String.format("Order event => %s", event.toString()));

        // create Message
        Message<RecSiguienteEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, topicName)
                .build();
        kafkaTemplate.send(message);
    }    
}
