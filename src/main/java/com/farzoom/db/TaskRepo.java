package com.farzoom.db;

import com.farzoom.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class TaskRepo {

    private final Map<Long, Task> tasks = new ConcurrentHashMap<>();
    private Long count = 0L;

    public Task save(Task task) {
        Long id = task.getId();
        if (id == 0) {
            id = ++count;
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

    public List<Task> findAllTasks(Page page) {
        return tasks.values().stream()
                .sorted(Comparator.comparing(Task::getDate))
                .skip((long) page.getNumber() * page.getSize())
                .limit(page.getSize())
                .collect(Collectors.toList());
    }
}
