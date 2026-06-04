package com.tasl.tasklist.messaging;


import com.tasl.tasklist.config.KafkaConfig;
import com.tasl.tasklist.config.RabbitMQConfig;
import com.tasl.tasklist.event.TaskCompletedEvent;
import com.tasl.tasklist.event.TaskCreatedEvent;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EventPublisher {

    private final AmqpTemplate amqpTemplate;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public EventPublisher(AmqpTemplate amqpTemplate, KafkaTemplate<String, Object> kafkaTemplate) {
        this.amqpTemplate = amqpTemplate;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishTaskCreated(TaskCreatedEvent event) {
        amqpTemplate.convertAndSend(RabbitMQConfig.TASK_EXCHANGE, RabbitMQConfig.TASK_CREATED_ROUTING_KEY, event);
        kafkaTemplate.send(KafkaConfig.TASK_CREATED_TOPIC, event);
    }

    public void publishTaskCompleted(TaskCompletedEvent event) {
        amqpTemplate.convertAndSend(RabbitMQConfig.TASK_EXCHANGE, RabbitMQConfig.TASK_COMPLETED_ROUTING_KEY, event);
        kafkaTemplate.send(KafkaConfig.TASK_COMPLETED_TOPIC, event);
    }
}