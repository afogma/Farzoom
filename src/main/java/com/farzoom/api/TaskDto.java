package com.farzoom.api;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
public class TaskDto {

    private final String name;
    private final String description;
    private final Instant date;

}
