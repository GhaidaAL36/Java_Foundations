package com.tasl.tasklist.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    public static final String TASK_CREATED_TOPIC = "task-created";
    public static final String TASK_COMPLETED_TOPIC = "task-completed";

    @Bean
    public NewTopic taskCreatedTopic() {
        return TopicBuilder.name(TASK_CREATED_TOPIC).build();
    }

    @Bean
    public NewTopic taskCompletedTopic() {
        return TopicBuilder.name(TASK_COMPLETED_TOPIC).build();
    }
}