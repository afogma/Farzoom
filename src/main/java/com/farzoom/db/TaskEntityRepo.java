package com.farzoom.db;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskEntityRepo  extends JpaRepository<TaskEntity, Long> {
}
