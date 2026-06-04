package com.tasl.tasklist.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String TASK_EXCHANGE = "task.exchange";
    public static final String TASK_CREATED_QUEUE = "task.created.queue";
    public static final String TASK_COMPLETED_QUEUE = "task.completed.queue";
    public static final String TASK_CREATED_ROUTING_KEY = "task.created";
    public static final String TASK_COMPLETED_ROUTING_KEY = "task.completed";

    @Bean
    public TopicExchange taskExchange() {
        return new TopicExchange(TASK_EXCHANGE);
    }

    @Bean
    public Queue taskCreatedQueue() {
        return new Queue(TASK_CREATED_QUEUE);
    }

    @Bean
    public Queue taskCompletedQueue() {
        return new Queue(TASK_COMPLETED_QUEUE);
    }

    @Bean
    public Binding taskCreatedBinding() {
        return BindingBuilder
                .bind(taskCreatedQueue())
                .to(taskExchange())
                .with(TASK_CREATED_ROUTING_KEY);
    }

    @Bean
    public Binding taskCompletedBinding() {
        return BindingBuilder
                .bind(taskCompletedQueue())
                .to(taskExchange())
                .with(TASK_COMPLETED_ROUTING_KEY);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}
