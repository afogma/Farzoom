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

    public Task save(Task task) {
        Long id = count + 1;
        if (task.getId() == 0) {
            task = new Task(id, task.getName(), task.getDescription());
        }
        tasks.put(id, task);
        return task;
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
