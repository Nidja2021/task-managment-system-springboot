package com.taskmanagmentsystem.tasks;

import com.taskmanagmentsystem.config.JwtService;
import com.taskmanagmentsystem.employees.Employee;
import com.taskmanagmentsystem.employees.EmployeeRepository;
import com.taskmanagmentsystem.exceptions.NotFoundException;
import com.taskmanagmentsystem.tasks.dto.CommentRequestDto;
import com.taskmanagmentsystem.tasks.dto.CommentResponseDto;
import com.taskmanagmentsystem.tasks.dto.TaskRequestDto;
import com.taskmanagmentsystem.tasks.dto.TaskResponseDto;
import com.taskmanagmentsystem.tasks.entity.Comment;
import com.taskmanagmentsystem.tasks.entity.Task;
import com.taskmanagmentsystem.tasks.repository.CommentRepository;
import com.taskmanagmentsystem.tasks.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final JwtService jwtService;
    private final EmployeeRepository employeeRepository;
    private final CommentRepository commentRepository;

    public ResponseEntity<List<TaskResponseDto>> findAllTasks() {
        var tasks = taskRepository.findAll()
                .stream().map(task ->
                        TaskResponseDto.builder()
                                .title(task.getTitle())
                                .description(task.getDescription())
                                .taskStatus(task.getTaskStatus())
                                .build()
                ).toList();
        return ResponseEntity.ok(tasks);
    }

    public ResponseEntity<TaskResponseDto> createTask(TaskRequestDto taskRequestDto, String header) {
        String token = header.substring(7);

        var email = jwtService.extractUsername(token);
        var employee = employeeRepository.findEmployeeByEmail(email)
                        .orElseThrow(() -> new NotFoundException("Employee does not exists."));

        saveTask(taskRequestDto, employee);

        TaskResponseDto taskResponseDto = createTaskResponseDto(taskRequestDto, employee);

        return ResponseEntity.ok(taskResponseDto);
    }

    public ResponseEntity<TaskResponseDto> findTaskById(UUID taskId) {
        var task = taskRepository.findById(taskId)
                .orElseThrow(() -> new NotFoundException("Task not found."));

        TaskResponseDto taskResponseDto = TaskResponseDto.builder()
                .title(task.getTitle())
                .description(task.getDescription())
                .taskStatus(task.getTaskStatus())
                .employeeID(task.getAssignedTo().getId())
                .build();

        return ResponseEntity.ok(taskResponseDto);
    }

    public ResponseEntity<TaskResponseDto> updateTaskById(UUID taskId, TaskRequestDto taskRequestDto) {
        var task = taskRepository.findById(taskId)
                .orElseThrow(() -> new NotFoundException("Task not found."));

        task.setTitle(taskRequestDto.title());
        task.setDescription(taskRequestDto.description());
        task.setTaskStatus(taskRequestDto.taskStatus());

        taskRepository.save(task);

        TaskResponseDto taskResponseDto = createTaskResponseDto(taskRequestDto, task.getAssignedTo());

        return ResponseEntity.ok(taskResponseDto);

    }

    public ResponseEntity<String> deleteTaskById(UUID taskId) {
        var task = taskRepository.findById(taskId)
                .orElseThrow(() -> new NotFoundException("Task not found."));

        taskRepository.deleteById(taskId);

        String message = "Task has been deleted by ID: " + taskId;

        return ResponseEntity.ok(message);
    }

    public ResponseEntity<List<TaskResponseDto>> findAllTasksByEmployee(UUID employeeId) {
        var employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new NotFoundException("Employee does not exists."));

        var tasksByEmployee = taskRepository.findAllTasksByEmployee(employeeId);

        return ResponseEntity.ok(tasksByEmployee);
    }

    public ResponseEntity<List<TaskResponseDto>> findAllTasksByStatus(TaskStatus taskStatus) {
        var tasksByStatus = taskRepository.findAllTasksByStatus(taskStatus);

        return ResponseEntity.ok(tasksByStatus);
    }

    public ResponseEntity<List<CommentResponseDto>> findAllCommentsByTaskId(UUID taskId) {
        var commentsByTaskId = commentRepository.findByTaskId(taskId);

        return ResponseEntity.ok(commentsByTaskId);
    }

    public ResponseEntity<CommentResponseDto> createCommentByTaskId(UUID taskId, CommentRequestDto commentRequestDto) {
        var task = taskRepository.findById(taskId)
                .orElseThrow(() -> new NotFoundException("Task does not exists."));

        var comment = Comment.builder()
                .text(commentRequestDto.text())
                .task(task)
                .build();

        var commentResponseDto = CommentResponseDto.builder()
                .text(commentRequestDto.text())
                .taskId(task.getId())
                .build();

        return ResponseEntity.ok(commentResponseDto);

    }

    private TaskResponseDto createTaskResponseDto(TaskRequestDto taskRequestDto, Employee employee) {
        return TaskResponseDto.builder()
                .title(taskRequestDto.title())
                .description(taskRequestDto.description())
                .taskStatus(taskRequestDto.taskStatus())
                .employeeID(employee.getId())
                .build();
    }

    private void saveTask(TaskRequestDto taskRequestDto, Employee employee) {
        Task task = Task.builder()
                .title(taskRequestDto.title())
                .description(taskRequestDto.description())
                .taskStatus(TaskStatus.NEW)
                .assignedTo(employee)
                .build();
        taskRepository.save(task);
    }



}
