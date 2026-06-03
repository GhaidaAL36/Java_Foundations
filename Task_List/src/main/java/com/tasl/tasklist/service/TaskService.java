package com.tasl.tasklist.service;

import com.tasl.tasklist.dto.TaskRequestDTO;
import com.tasl.tasklist.dto.TaskResponseDTO;
import com.tasl.tasklist.exception.TaskNotFoundException;
import com.tasl.tasklist.model.Task;
import com.tasl.tasklist.repository.TaskRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
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
        return toResponse(saved);
    }

    public TaskResponseDTO updateTask(UUID id, TaskRequestDTO task) {
        if (this.taskRepository.existsById(id)) {
            Task t = toEntity(task);
            t.setId(id);
            Task saved = taskRepository.save(t);
            return toResponse(saved);
        }
        throw new TaskNotFoundException(id);
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
