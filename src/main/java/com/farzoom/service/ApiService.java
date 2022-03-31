package com.farzoom.service;

import com.farzoom.db.TaskEntityRepo;
import com.farzoom.db.TaskRepo;
import com.farzoom.api.TaskDto;
import com.farzoom.model.Task;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApiService {

    private static final Logger logger = LoggerFactory.getLogger(ApiService.class);

    private final TaskRepo taskRepo;

    public List<TaskDto> findAllTasks(Pageable pageable) {

        List<TaskDto> taskListDTOs = taskRepo.findAllTasks(pageable).stream()
                .map(task -> task.convertToDto(task))
                .collect(Collectors.toList());
        logger.info("requesting tasks list");

        return taskListDTOs;
    }

    public TaskDto findTaskById(Long id) {
        Task task = taskRepo.findById(id);
        TaskDto taskDto = task.convertToDto(task);
        logger.info("requesting description for task id: {}", id);
        return taskDto;
    }

    public void deleteTask(Long id) {
        taskRepo.delete(id);
        logger.info("task with id {} removed", id);
    }

    public TaskDto updateTasksName(Long id, String name) {
        Task task = taskRepo.findById(id);
        task = task.setName(name);
        taskRepo.save(task);
        TaskDto taskDto = task.convertToDto(task);
        logger.info("name for task id {} updated", id);
        return taskDto;
    }

    public TaskDto updateTasksDescription(Long id, String description) {
        Task task = taskRepo.findById(id);
        task = task.setDescription(description);
        taskRepo.save(task);
        TaskDto taskDto = task.convertToDto(task);
        logger.info("name for task id {} updated", id);
        return taskDto;
    }

    public Task createNewTask(TaskDto taskDto) {
        Task task = new Task(0L, taskDto.getName(), taskDto.getDescription());
        taskRepo.save(task);
        logger.info("creating new task with id {} ", task.getId());
        return task;
    }
}