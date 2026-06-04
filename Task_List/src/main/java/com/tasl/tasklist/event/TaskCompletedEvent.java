package com.tasl.tasklist.event;

import lombok.Data;

import java.util.UUID;


@Data
public class TaskCompletedEvent {

    UUID id;
    String title;
    private String correlationId;
}
