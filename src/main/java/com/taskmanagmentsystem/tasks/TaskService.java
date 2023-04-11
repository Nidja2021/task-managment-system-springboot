package com.taskmanagmentsystem.tasks;

import com.taskmanagmentsystem.config.JwtService;
import com.taskmanagmentsystem.employees.Employee;
import com.taskmanagmentsystem.employees.EmployeeRepository;
import com.taskmanagmentsystem.exceptions.EmployeeNotFoundException;
import com.taskmanagmentsystem.tasks.dto.TaskRequestDto;
import com.taskmanagmentsystem.tasks.dto.TaskResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final JwtService jwtService;
    private final EmployeeRepository employeeRepository;

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
                        .orElseThrow(() -> new EmployeeNotFoundException("Employee does not exists."));

        saveTask(taskRequestDto, employee);

        TaskResponseDto taskResponseDto = createTaskResponseDto(taskRequestDto);

        return ResponseEntity.ok(taskResponseDto);
    }

    private TaskResponseDto createTaskResponseDto(TaskRequestDto taskRequestDto) {
        return TaskResponseDto.builder()
                .title(taskRequestDto.title())
                .description(taskRequestDto.description())
                .taskStatus(taskRequestDto.taskStatus())
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
