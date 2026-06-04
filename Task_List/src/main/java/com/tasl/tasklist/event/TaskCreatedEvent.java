package com.tasl.tasklist.event;

import lombok.Data;

import java.util.UUID;

@Data
public class TaskCreatedEvent {

    UUID id;
    String title;
    String assignee;
    private String correlationId;

}
