package com.letus.rutasservice.config;

import java.util.HashMap;

// import java.util.HashMap;
// import java.util.Map;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.letus.dto.CheckpointEvent;

@Configuration
public class KafkaTopicConfig {

    @Value("${spring.kafka.topic.name2}")
    private String topicName;

    @Value("${spring.kafka.topic.name3}")
    private String topicName2;

    @Value("${spring.kafka.producer.bootstrap-servers}")
    private  String PORT;


    @Bean
        public ProducerFactory<String, CheckpointEvent> checkpointProducerFactory() {

            HashMap<String, Object> configProps = new HashMap<String, Object>();
            configProps.put(
                    ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                    PORT);
            configProps.put(
                    ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                    StringSerializer.class);
            configProps.put(
                    ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                    JsonSerializer.class);
            return new DefaultKafkaProducerFactory<>(configProps);
        }
        @Bean
        public ProducerFactory<String, String> cloudraProducerFactory() {

            HashMap<String, Object> configProps = new HashMap<String, Object>();
            configProps.put(
                    ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                    PORT);
            configProps.put(
                    ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                    StringSerializer.class);
            configProps.put(
                    ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                    JsonSerializer.class);
            return new DefaultKafkaProducerFactory<>(configProps);
        }

        @Bean(name = "checkpoints")
        public KafkaTemplate<String, CheckpointEvent> checkpointKafkaTemplate() {
            return new KafkaTemplate<>(checkpointProducerFactory());
        }

        @Bean(name = "cloudera")
        public KafkaTemplate<String, String> clouderaKafkaTemplate() {
            return new KafkaTemplate<>(cloudraProducerFactory());
        }
    // spring bean for kafka topic
    @Bean
    public NewTopic topic(){
        return TopicBuilder.name(topicName)
                .build();
    }

    @Bean
    public NewTopic topic2(){
        return TopicBuilder.name(topicName2)
                .build();
    }

}
