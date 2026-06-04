package com.tasl.tasklist.service;

import com.tasl.tasklist.dto.TaskRequestDTO;
import com.tasl.tasklist.dto.TaskResponseDTO;
import com.tasl.tasklist.event.TaskCompletedEvent;
import com.tasl.tasklist.event.TaskCreatedEvent;
import com.tasl.tasklist.exception.TaskNotFoundException;
import com.tasl.tasklist.messaging.EventPublisher;
import com.tasl.tasklist.model.Task;
import com.tasl.tasklist.model.TaskStatus;
import com.tasl.tasklist.repository.TaskRepository;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final EventPublisher eventPublisher;

    public TaskService(TaskRepository taskRepository, EventPublisher eventPublisher) {
        this.taskRepository = taskRepository;
        this.eventPublisher = eventPublisher;
    }

    public List<TaskResponseDTO> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        List<TaskResponseDTO> taskResponseDTOs = tasks.stream().map(task -> toResponse(task)).toList();
        return taskResponseDTOs;
    }


    public TaskResponseDTO getTaskById(UUID id) {
        Task task = taskRepository.findById(id).orElse(null);
        if (task == null) throw new TaskNotFoundException(id);
        return toResponse(task);
    }

    public TaskResponseDTO createTask(TaskRequestDTO dto) {
        Task task = toEntity(dto);
        Task saved = taskRepository.save(task);

        TaskCreatedEvent event = new TaskCreatedEvent();
        event.setId(saved.getId());
        event.setTitle(saved.getTitle());
        event.setAssignee(saved.getAssignee());
        event.setCorrelationId(MDC.get("correlationId"));

        eventPublisher.publishTaskCreated(event);
        return toResponse(saved);
    }

    public TaskResponseDTO updateTask(UUID id, TaskRequestDTO dto) {

        Task existing = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        existing.setTitle(dto.getTitle());
        existing.setDescription(dto.getDescription());
        existing.setStatus(dto.getStatus());
        existing.setAssignee(dto.getAssignee());

        Task saved = taskRepository.save(existing);

        if (saved.getStatus() == TaskStatus.DONE) {
            TaskCompletedEvent event = new TaskCompletedEvent();
            event.setId(saved.getId());
            event.setTitle(saved.getTitle());
            event.setCorrelationId(MDC.get("correlationId"));
            eventPublisher.publishTaskCompleted(event);
        }
        return toResponse(saved);
    }

    public boolean deleteTask(UUID id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private Task toEntity(TaskRequestDTO taskRequestDTO) {
        Task task = new Task();
        task.setTitle(taskRequestDTO.getTitle());
        task.setDescription(taskRequestDTO.getDescription());
        task.setStatus(taskRequestDTO.getStatus());
        task.setAssignee(taskRequestDTO.getAssignee());
        return task;
    }

    private TaskResponseDTO toResponse(Task task) {
        TaskResponseDTO taskResponseDTO = new TaskResponseDTO();
        taskResponseDTO.setId(task.getId());
        taskResponseDTO.setTitle(task.getTitle());
        taskResponseDTO.setDescription(task.getDescription());
        taskResponseDTO.setStatus(task.getStatus());
        taskResponseDTO.setAssignee(task.getAssignee());
        taskResponseDTO.setCreatedAt(task.getCreatedAt());
        taskResponseDTO.setUpdatedAt(task.getUpdatedAt());
        return taskResponseDTO;
    }

}
