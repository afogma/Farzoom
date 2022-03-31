package com.farzoom.api;

import com.farzoom.model.Task;
import com.farzoom.service.ApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class ApiController {

    private final ApiService apiService;

    @PostMapping("/task")
    public TaskDto addNewTask(@RequestBody TaskDto taskDto) {
        Task task = apiService.createNewTask(taskDto);
        return task.convertToDto();
    }

    @GetMapping("/task/{id}")
    public TaskDto showTaskById(@PathVariable Long id) {
        Task task = apiService.findTaskById(id);
        return task.convertToDto();
    }

    @GetMapping("/tasks")
    public List<TaskDto> showAllTasks(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        return apiService.findAllTasks(pageable);
    }

    @PutMapping("/task/{id}/update-name")
    public TaskDto updateTaskName(@PathVariable Long id, @RequestBody String name) {
        Task task = apiService.updateTasksName(id, name);
        return task.convertToDto();
    }

    @PutMapping("/task/{id}/update-description")
    public TaskDto updateTaskDescription(@PathVariable Long id, @RequestBody String description) {
        Task task = apiService.updateTasksDescription(id, description);
        return task.convertToDto();
    }

    @DeleteMapping("/task/{id}")
    public void deleteTask(@PathVariable Long id) {
        apiService.deleteTask(id);
    }
}
