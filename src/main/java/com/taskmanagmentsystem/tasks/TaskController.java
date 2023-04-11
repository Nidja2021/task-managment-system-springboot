package com.taskmanagmentsystem.tasks;

import com.taskmanagmentsystem.tasks.dto.TaskRequestDto;
import com.taskmanagmentsystem.tasks.dto.TaskResponseDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<List<TaskResponseDto>> getAllTasks() {
        return taskService.findAllTasks();
    }

    @PostMapping
    public ResponseEntity<TaskResponseDto> createTask(
            @RequestBody TaskRequestDto taskRequestDto,
            @RequestHeader("Authorization") String header
    ) {
        return taskService.createTask(taskRequestDto, header);
    }
}
