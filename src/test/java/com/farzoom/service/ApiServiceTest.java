package com.farzoom.service;

import com.farzoom.api.TaskDto;
import com.farzoom.db.TaskRepo;
import com.farzoom.model.Task;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ApiServiceTest {

    private final TaskRepo taskRepo = mock(TaskRepo.class);
    private final ApiService apiService = new ApiService(taskRepo);

    @Test
    void createNewTask() {
        Task task = getTask();
        TaskDto taskDto = getTaskDto();
        when(taskRepo.save(any(Task.class))).thenReturn(task);
        Task testTask = apiService.createNewTask(taskDto);
        assertEquals(1L, testTask.getId());
        assertEquals("task name", testTask.getName());
        assertEquals("task description", testTask.getDescription());
    }

    @Test
    void findTaskById() {
        Task task = getTask();
        when(taskRepo.findById(1L)).thenReturn(task);
        Task testTask = apiService.findTaskById(1L);
        assertEquals(task, testTask);
    }

    @Test
    void findAllTasks() {
        List<Task> taskList = getTaskList();
        Pageable pageable = PageRequest.of(0, 3);
        when(taskRepo.findAllTasks(pageable)).thenReturn(taskList);
        List<TaskDto> testTaskList = apiService.findAllTasks(pageable);
        assertEquals(taskList.get(0).getId(), testTaskList.get(0).getId());
        assertEquals(taskList.get(1).getName(), testTaskList.get(1).getName());
        assertEquals(taskList.get(2).getDescription(), testTaskList.get(2).getDescription());
    }

    @Test
    void updateTasksName() {
        Task task = getTask();
        when(taskRepo.findById(1L)).thenReturn(task);
        Task testTask = apiService.updateTasksName(1L, "task new name");
        assertEquals("task new name", testTask.getName());
    }

    @Test
    void updateTasksDescription() {
        Task task = getTask();
        when(taskRepo.findById(1L)).thenReturn(task);
        Task testTask = apiService.updateTasksDescription(1L, "task new description");
        assertEquals("task new description", testTask.getDescription());
    }

    @Test
    void deleteTask() {
        Task task = getTask();
        when(taskRepo.findById(1L)).thenReturn(task);
        apiService.deleteTask(1L);
        assertNotNull(task);
        verify(taskRepo).delete(task.getId());
    }

    private Task getTask() {
        return new Task(1L, "task name", "task description");
    }

    private TaskDto getTaskDto() {
        return new TaskDto(0L, "task name 1", "task description", Instant.parse("2022-01-20T18:00:00.00Z"));
    }

    private List<Task> getTaskList() {
        List<Task> taskList = List.of(
                new Task(1L, "task name 1", "task description"),
                new Task(2L, "task name2", "task description"),
                new Task(3L, "task name3", "task description")
        );
        return taskList;
    }
}