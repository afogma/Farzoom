package com.farzoom.api;

import com.farzoom.service.ApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class ApiController {

    private final ApiService apiService;

    @GetMapping("/tasks")
    public Page<TaskDto> showAllTasks(@PageableDefault(page = 0, size = 10)
                                      @SortDefault(sort = "date", direction = Sort.Direction.ASC)
                                              Pageable pageable) {
        return apiService.findAllTasks(pageable);
    }

    @GetMapping("/task/{id}")
    public String showTaskById(@PathVariable Long id) {
        return apiService.findTaskById(id);
    }

    @PostMapping("/task")
    public ResponseEntity addNewTask(@RequestBody TaskDto taskDto) {
        apiService.createNewTask(taskDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/task/update-name")
    public TaskDto updateTaskName(@RequestParam Long id, @RequestBody String name) {
        TaskDto taskDto = apiService.updateTasksName(id, name);
        return taskDto;
    }

    @PutMapping("/task/update-description")
    public TaskDto updateTaskDescription(@RequestParam Long id, @RequestBody String description) {
        TaskDto taskDto = apiService.updateTasksDescription(id, description);
        return taskDto;
    }

    @DeleteMapping("/task/{id}")
    public ResponseEntity deleteTask(@PathVariable Long id) {
        apiService.deleteTask(id);
        return ResponseEntity.ok("description for task with id: " + id + " deleted");
    }
}
