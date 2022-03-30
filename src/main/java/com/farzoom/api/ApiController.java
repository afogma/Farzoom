package com.farzoom.api;

import com.farzoom.service.ApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class ApiController {

    private final ApiService apiService;

    @GetMapping("/tasks")
    public List<TaskDto> showAllTasks(@RequestParam Page pages) {
        return apiService.findAllTasks(pages);
    }

    @GetMapping("/task")
    public String showTaskById(@RequestParam Long id) {
        return apiService.findTaskById(id);
    }

    @PostMapping("/task/add")
    public ResponseEntity addNewTask(@RequestBody TaskDto taskDto) {
        return ResponseEntity.ok("task added successfully");
    }

    @PutMapping
    public ResponseEntity updateTaskName(@RequestParam Long id, @RequestParam String name) {
            apiService.updateTasksName(id, name);
        return ResponseEntity.ok("name for task with id: " + id + " was updated");
    }

    @PutMapping
    public ResponseEntity updateTaskDescription(@RequestParam Long id, @RequestParam String description) {
        apiService.updateTasksDescription(id, description);
        return ResponseEntity.ok("name for task with id: " + id + " was updated");
    }

}
