package com.tasl.tasklist.dto;

import com.tasl.tasklist.model.TaskStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class TaskResponseDTO {
    private UUID id;
    private String title;
    private String description;
    private TaskStatus status;
    private String assignee;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
