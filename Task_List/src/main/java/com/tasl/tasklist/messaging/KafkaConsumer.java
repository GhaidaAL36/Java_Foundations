package com.tasl.tasklist.messaging;

import com.tasl.tasklist.config.KafkaConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = KafkaConfig.TASK_CREATED_TOPIC)
    public void handleTaskCreated(String message) {
        log.info("Kafka - Task Created: {}", message);
    }

    @KafkaListener(topics = KafkaConfig.TASK_COMPLETED_TOPIC)
    public void handleTaskCompleted(String message) {
        log.info("Kafka - Task Completed: {}", message);
    }
}