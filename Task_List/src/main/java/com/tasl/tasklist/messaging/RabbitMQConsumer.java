package com.tasl.tasklist.messaging;

import com.tasl.tasklist.config.RabbitMQConfig;
import com.tasl.tasklist.event.TaskCompletedEvent;
import com.tasl.tasklist.event.TaskCreatedEvent;
import org.jboss.logging.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {

    private static final Logger log = LoggerFactory.getLogger(RabbitMQConsumer.class);

    @RabbitListener(queues = RabbitMQConfig.TASK_CREATED_QUEUE)
    public void handleTaskCreated(TaskCreatedEvent event) {
        MDC.put("correlationId", event.getCorrelationId());
        log.info("RabbitMQ - Task Created: id={}, title={}, assignee={}",
                event.getId(), event.getTitle(), event.getAssignee());
        MDC.clear();
    }

    @RabbitListener(queues = RabbitMQConfig.TASK_COMPLETED_QUEUE)
    public void handleTaskCompleted(TaskCompletedEvent event) {
        MDC.put("correlationId", event.getCorrelationId());
        log.info("RabbitMQ - Task Completed: id={}, title={}",
                event.getId(), event.getTitle());
        MDC.clear();
    }
}