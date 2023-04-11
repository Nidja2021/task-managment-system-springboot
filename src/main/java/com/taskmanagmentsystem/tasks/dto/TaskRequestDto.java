package com.taskmanagmentsystem.tasks.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.taskmanagmentsystem.employees.Employee;
import com.taskmanagmentsystem.tasks.TaskStatus;

public record TaskRequestDto(
        String title,
        String description,
        @JsonProperty("task_status")
        TaskStatus taskStatus
) {
}
