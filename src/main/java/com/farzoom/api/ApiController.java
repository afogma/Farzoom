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

    @GetMapping("/task")
    public String showTaskById(@RequestParam Long id) {
        return apiService.findTaskById(id);
    }

    @PostMapping("/task/add")
    public ResponseEntity addNewTask(@RequestBody TaskDto taskDto) {
        apiService.createNewTask(taskDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/task/update-name")
    public ResponseEntity updateTaskName(@RequestParam Long id, @RequestBody String name) {
        apiService.updateTasksName(id, name);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/task/update-description")
    public ResponseEntity updateTaskDescription(@RequestParam Long id, @RequestBody String description) {
        apiService.updateTasksDescription(id, description);
        return ResponseEntity.ok("description for task with id: " + id + " updated");
    }

    @DeleteMapping("/task/del")
    public ResponseEntity deleteTask(@PathVariable Long id) {
        apiService.deleteTask(id);
        return ResponseEntity.ok("description for task with id: " + id + " deleted");
    }
}
