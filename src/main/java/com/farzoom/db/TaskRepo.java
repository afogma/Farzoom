package com.farzoom.db;

import com.farzoom.model.Task;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class TaskRepo {

    private final Map<Long, Task> tasks = new ConcurrentHashMap<>();
    private final AtomicLong count = new AtomicLong(0);

    public Task save(Task task) {
        Long id = task.getId();
        if (id == 0) {
            id = count.incrementAndGet();
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
        return tasks.values().stream()
                .sorted(Comparator.comparing(Task::getDate))
//                .skip((long) page.getNumber() * page.getSize())
//                .limit(page.getSize())
                .collect(Collectors.toList());
    }
}
