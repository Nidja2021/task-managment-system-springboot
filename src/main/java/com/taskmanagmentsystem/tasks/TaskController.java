package com.taskmanagmentsystem.tasks;

import com.taskmanagmentsystem.tasks.dto.CommentRequestDto;
import com.taskmanagmentsystem.tasks.dto.CommentResponseDto;
import com.taskmanagmentsystem.tasks.dto.TaskRequestDto;
import com.taskmanagmentsystem.tasks.dto.TaskResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDto> getTaksById(@PathVariable("id") UUID taskId) {
        return taskService.findTaskById(taskId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDto> updateTaksById(
            @PathVariable("id") UUID taskId,
            @RequestBody TaskRequestDto taskRequestDto
    ) {
        return taskService.updateTaskById(taskId, taskRequestDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTaksById(@PathVariable("id") UUID taskId) {
        return taskService.deleteTaskById(taskId);
    }

    @GetMapping("/assigned_to/{employeeId}")
    public ResponseEntity<List<TaskResponseDto>> getAllTasksByEmployee(@PathVariable("employeeId") UUID assignedTo ) {
        return taskService.findAllTasksByEmployee(assignedTo);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<TaskResponseDto>> getAllTasksByStatus(@PathVariable("status") TaskStatus status ) {
        return taskService.findAllTasksByStatus(status);
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<List<CommentResponseDto>> getAllCommentsByTaskId(@PathVariable("id") UUID taskId) {
        return taskService.findAllCommentsByTaskId(taskId);
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<CommentResponseDto> createCommentByTaskId(
            @PathVariable("id") UUID taskId,
            @RequestBody CommentRequestDto commentRequestDto
    ) {
        return taskService.createCommentByTaskId(taskId, commentRequestDto);
    }
}
