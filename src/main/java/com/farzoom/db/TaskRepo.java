package com.farzoom.db;

import com.farzoom.api.TaskDto;
import com.farzoom.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class TaskRepo {

    private final Map<Long, Task> tasks = new HashMap<>();
    private final AtomicLong count = new AtomicLong(0);

    public synchronized Task save(Task task) {
        Long id = task.getId();
        if (id == 0) {
            id = count.incrementAndGet();
            task = new Task(id, task.getName(), task.getDescription());
        }
        tasks.put(id, task);
        return task;
    }

    public synchronized void delete(Long id) {
        tasks.remove(id);
    }

    public synchronized Task findById(Long id) {
        return tasks.get(id);
    }

    public synchronized List<Task> findAllTasks(Pageable pageable) {
        return tasks.values().stream()
                .sorted(Comparator.comparing(Task::getDate))
                .skip(pageable.getOffset())
                .limit(pageable.getPageSize())
                .collect(Collectors.toList());
    }
}
