package com.farzoom.db;

import com.farzoom.api.TaskDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

import static javax.persistence.GenerationType.SEQUENCE;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tasks")
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = SEQUENCE)
    private Long id;

    private String name;

    private String description;

    private Instant date;

    public TaskDto convertToDto(TaskEntity task) {
        return new TaskDto(task.getId(), task.getName(), task.getDescription());
    }

    public TaskEntity convertFromDto(TaskDto taskDto) {
        return new TaskEntity(0L, taskDto.getName(), taskDto.getDescription(), taskDto.getDate());
    }
}
