package com.farzoom.api;

import com.farzoom.service.ApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class ApiController {

    private final ApiService apiService;

    @GetMapping("/tasks")
    public List<TaskDto> showAllTasks() {
        return apiService.findAllTasks();
    }

    @GetMapping("/task")
    public String showTaskById(@RequestParam Long id) {
        return apiService.findTaskById(id);
    }



}
