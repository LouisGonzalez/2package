package com.letus.paquetesservice.config;

// import java.util.HashMap;
// import java.util.Map;

// import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
// import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaTopicConfig {

    @Value("${spring.kafka.topic.name}")
    private String topicName;

    // spring bean for kafka topic
    @Bean
    public NewTopic topic(){
        return TopicBuilder.name(topicName)
                .build();
    }
    // @Value(value = "${spring.kafka.bootstrap-servers}")
    // private String bootstrapAddress;

    // @Bean
    // public KafkaAdmin kafkaAdmin() {
    //     Map<String, Object> configs = new HashMap<>();
    //     configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
    //     return new KafkaAdmin(configs);
    // }
    
    // @Bean
    // public NewTopic topic1() {
    //      return new NewTopic("baeldung", 1, (short) 1);
    // }
}
