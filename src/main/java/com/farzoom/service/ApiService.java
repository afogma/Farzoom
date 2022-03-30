package com.farzoom.service;

import com.farzoom.db.TaskEntity;
import com.farzoom.model.TaskDto;
import com.farzoom.db.Task;
import com.farzoom.db.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApiService {

    private static final Logger logger = LoggerFactory.getLogger(ApiService.class);

    private final Map<Long, Task> tasks = new ConcurrentHashMap<>();

    private final TaskRepository taskRepository;

    public List<TaskDto> findAllTasks() {
        List<TaskEntity> taskList = taskRepository.findAll();

        List<TaskDto> taskListDTOsFromDB = taskList.stream()
                .map(task -> task.convertToDto(task))
                .sorted(Comparator.comparing(TaskDto::getDescription))
                .collect(Collectors.toList());

        List<TaskDto> taskListDTOs = tasks.values().stream()
                .map(task -> task.convertToDto(task))
                .sorted(Comparator.comparing(TaskDto::getDescription))
                .collect(Collectors.toList());

        return taskListDTOs;
    }

    public String findTaskById(Long id) {
        Task task = tasks.get(id);
        return task.getDescription();
    }


}