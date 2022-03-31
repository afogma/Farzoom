package com.farzoom.api;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
public class TaskDto {

    private Long id;
    private String name;
    private String description;
    private final Instant date = Instant.now();
}
