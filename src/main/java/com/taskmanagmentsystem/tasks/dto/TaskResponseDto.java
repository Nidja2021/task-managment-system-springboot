package com.taskmanagmentsystem.tasks.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.taskmanagmentsystem.employees.Employee;
import com.taskmanagmentsystem.tasks.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskResponseDto {
    private String title;
    private String description;
    @JsonProperty("task_status")
    private TaskStatus taskStatus;
    private UUID employeeID;
}
