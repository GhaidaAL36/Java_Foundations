package com.tasl.tasklist.controller;


import com.tasl.tasklist.dto.TaskRequestDTO;
import com.tasl.tasklist.dto.TaskResponseDTO;
import com.tasl.tasklist.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<TaskResponseDTO> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable UUID id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @PostMapping
    public TaskResponseDTO createTask(@RequestBody @Valid TaskRequestDTO task) {

        return taskService.createTask(task);
    }

    @PutMapping("/{id}")
    public TaskResponseDTO updateTask(@PathVariable UUID id, @RequestBody @Valid TaskRequestDTO task) {

        return taskService.updateTask(id, task);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable UUID id) {
        boolean deleted = taskService.deleteTask(id);
        if (!deleted) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().build();
    }

}
