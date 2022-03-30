package com.farzoom.db;

import com.farzoom.model.Task;
import com.farzoom.api.TaskDto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class TaskRepo {

    private Map<Long, Task> tasks = new ConcurrentHashMap<>();
    private Long count = 0L;

    public void save(TaskDto taskDto) {
        Long id = count + 1;
        Task task = new Task(id, taskDto.getName(), taskDto.getDescription());
        tasks.put(id, task);
    }

    public void updateName(Long id, String name) {
        Task task = tasks.get(id);
        task.setName(name);
        tasks.put(id, task);
    }

    public void updateDescription(Long id, String description) {
        Task task = tasks.get(id);
        task.setDescription(description);
        tasks.put(id, task);
    }

    public void delete(Long id) {
        tasks.remove(id);
    }

    public Task findById(Long id) {
        return tasks.get(id);
    }

    public List<Task> findAllTasks() {
        return new ArrayList<>(tasks.values());
    }
}
