package com.farzoom.service;

import com.farzoom.db.TaskEntity;
import com.farzoom.db.TaskEntityRepo;
import com.farzoom.db.TaskRepo;
import com.farzoom.api.TaskDto;
import com.farzoom.model.Task;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApiService {

    private static final Logger logger = LoggerFactory.getLogger(ApiService.class);

    private final TaskEntityRepo taskEntityRepo;
    private final TaskRepo taskRepo;

    public List<TaskDto> findAllTasks(Page pages) {
        List<TaskDto> taskListDTOs = taskRepo.findAllTasks(pages).stream()
                .map(task -> task.convertToDto(task))
                .sorted(Comparator.comparing(TaskDto::getDate))
                .collect(Collectors.toList());

        logger.info("requesting tasks list");
        return taskListDTOs;
    }

    public String findTaskById(Long id) {
        Task task = taskRepo.findById(id);
        logger.info("requesting description for task id: {}", id);
        return task.getDescription();
    }

    public void deleteTask(Long id) {
        taskRepo.delete(id);
        logger.info("task with id {} was removed", id);
    }

    public Task updateTasksName(Long id, String name) {
        Task task = taskRepo.findById(id);
        task.setName(name);
        taskRepo.save(task);
        return task;
    }

    public Task updateTasksDescription(Long id, String description) {
        Task task = taskRepo.findById(id);
        task.setDescription(description);
        taskRepo.save(task);
        return task;
    }

    public Task createNewTask(TaskDto taskDto) {
        Task task = new Task(0L, taskDto.getName(), taskDto.getDescription());
        taskRepo.save(task);
        return task;
    }
}