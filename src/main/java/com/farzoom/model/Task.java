package com.farzoom.model;

import com.farzoom.api.TaskDto;
import lombok.*;

import java.time.Instant;

@Getter
public final class Task {

    private final Long id;
    private final String name;
    private final String description;
    private final Instant date = Instant.now();

    public Task(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Task setName(String name) {
        return new Task(this.id, name, this.description);
    }

    public Task setDescription(String description) {
        return new Task(this.id, this.name, description);
    }

    public TaskDto convertToDto(Task task) {
        return new TaskDto(task.getName(), task.getDescription(), task.getDate());
    }

    public Task convertFromDto(TaskDto taskDto) {
        return new Task(1L, taskDto.getName(), taskDto.getDescription());
    }

}
