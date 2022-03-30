package com.farzoom.service;

import com.farzoom.db.TaskEntity;
import com.farzoom.db.TaskEntityRepo;
import com.farzoom.db.TaskRepo;
import com.farzoom.model.TaskDto;
import com.farzoom.model.Task;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    public List<TaskDto> findAllTasks() {
        List<TaskEntity> taskList = taskEntityRepo.findAll();

        List<TaskDto> taskListDTOsFromDB = taskList.stream()
                .map(task -> task.convertToDto(task))
                .sorted(Comparator.comparing(TaskDto::getDescription))
                .collect(Collectors.toList());

        List<TaskDto> taskListDTOs = taskRepo.findAllTasks().stream()
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


}