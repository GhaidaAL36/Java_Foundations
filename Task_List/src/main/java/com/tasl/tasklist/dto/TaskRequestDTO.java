package com.tasl.tasklist.dto;


import com.tasl.tasklist.model.TaskStatus;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;


@Data
public class TaskRequestDTO {

    @NotBlank
    private String title;
    private String description;
    private TaskStatus status;
    private String assignee;
}
