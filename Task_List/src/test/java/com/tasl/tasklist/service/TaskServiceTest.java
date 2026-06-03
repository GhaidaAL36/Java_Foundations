package com.tasl.tasklist.service;

import com.tasl.tasklist.dto.TaskRequestDTO;
import com.tasl.tasklist.dto.TaskResponseDTO;
import com.tasl.tasklist.exception.TaskNotFoundException;
import com.tasl.tasklist.model.Task;
import com.tasl.tasklist.model.TaskStatus;
import com.tasl.tasklist.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    // -----------------------------------------------
    // getAllTasks
    // -----------------------------------------------
    @Test
    void getAllTasks_shouldReturnListOfTasks() {
        // ARRANGE
        Task task = new Task();
        task.setTitle("Test task");
        task.setStatus(TaskStatus.TODO);
        task.setAssignee("Ghaida");

        when(taskRepository.findAll()).thenReturn(List.of(task));

        // ACT
        List<TaskResponseDTO> result = taskService.getAllTasks();

        // ASSERT
        assertEquals(1, result.size());
        assertEquals("Test task", result.get(0).getTitle());
    }

    @Test
    void getAllTasks_shouldReturnEmptyList_whenNoTasks() {
        // ARRANGE
        when(taskRepository.findAll()).thenReturn(List.of());

        // ACT
        List<TaskResponseDTO> result = taskService.getAllTasks();

        // ASSERT
        assertEquals(0, result.size());
    }

    // -----------------------------------------------
    // getTaskById
    // -----------------------------------------------
    @Test
    void getTaskById_shouldReturnTask_whenExists() {
        // ARRANGE
        UUID id = UUID.randomUUID();
        Task task = new Task();
        task.setId(id);
        task.setTitle("Found task");
        task.setStatus(TaskStatus.TODO);

        when(taskRepository.findById(id)).thenReturn(Optional.of(task));

        // ACT
        TaskResponseDTO result = taskService.getTaskById(id);

        // ASSERT
        assertEquals(id, result.getId());
        assertEquals("Found task", result.getTitle());
    }

    @Test
    void getTaskById_shouldThrowException_whenNotFound() {
        // ARRANGE
        UUID id = UUID.randomUUID();
        when(taskRepository.findById(id)).thenReturn(Optional.empty());

        // ACT & ASSERT
        assertThrows(TaskNotFoundException.class, () -> taskService.getTaskById(id));
    }

    // -----------------------------------------------
    // createTask
    // -----------------------------------------------
    @Test
    void createTask_shouldReturnSavedTask() {
        // ARRANGE
        TaskRequestDTO dto = new TaskRequestDTO();
        dto.setTitle("New task");
        dto.setDescription("Description");
        dto.setStatus(TaskStatus.TODO);
        dto.setAssignee("Ghaida");

        Task saved = new Task();
        saved.setId(UUID.randomUUID());
        saved.setTitle("New task");
        saved.setDescription("Description");
        saved.setStatus(TaskStatus.TODO);
        saved.setAssignee("Ghaida");

        when(taskRepository.save(any(Task.class))).thenReturn(saved);

        // ACT
        TaskResponseDTO result = taskService.createTask(dto);

        // ASSERT
        assertNotNull(result.getId());
        assertEquals("New task", result.getTitle());
        assertEquals(TaskStatus.TODO, result.getStatus());
    }

    // -----------------------------------------------
    // updateTask
    // -----------------------------------------------
    @Test
    void updateTask_shouldReturnUpdatedTask_whenExists() {
        // ARRANGE
        UUID id = UUID.randomUUID();

        TaskRequestDTO dto = new TaskRequestDTO();
        dto.setTitle("Updated task");
        dto.setStatus(TaskStatus.IN_PROGRESS);
        dto.setAssignee("Ghaida");

        Task saved = new Task();
        saved.setId(id);
        saved.setTitle("Updated task");
        saved.setStatus(TaskStatus.IN_PROGRESS);
        saved.setAssignee("Ghaida");

        when(taskRepository.existsById(id)).thenReturn(true);
        when(taskRepository.save(any(Task.class))).thenReturn(saved);

        // ACT
        TaskResponseDTO result = taskService.updateTask(id, dto);

        // ASSERT
        assertEquals(id, result.getId());
        assertEquals("Updated task", result.getTitle());
        assertEquals(TaskStatus.IN_PROGRESS, result.getStatus());
    }

    @Test
    void updateTask_shouldThrowException_whenNotFound() {
        // ARRANGE
        UUID id = UUID.randomUUID();
        TaskRequestDTO dto = new TaskRequestDTO();
        dto.setTitle("Updated task");

        when(taskRepository.existsById(id)).thenReturn(false);

        // ACT & ASSERT
        assertThrows(TaskNotFoundException.class, () -> taskService.updateTask(id, dto));
    }

    // -----------------------------------------------
    // deleteTask
    // -----------------------------------------------
    @Test
    void deleteTask_shouldReturnTrue_whenExists() {
        // ARRANGE
        UUID id = UUID.randomUUID();
        when(taskRepository.existsById(id)).thenReturn(true);

        // ACT
        boolean result = taskService.deleteTask(id);

        // ASSERT
        assertTrue(result);
        verify(taskRepository, times(1)).deleteById(id);
    }

    @Test
    void deleteTask_shouldReturnFalse_whenNotFound() {
        // ARRANGE
        UUID id = UUID.randomUUID();
        when(taskRepository.existsById(id)).thenReturn(false);

        // ACT
        boolean result = taskService.deleteTask(id);

        // ASSERT
        assertFalse(result);
        verify(taskRepository, never()).deleteById(id);
    }
}