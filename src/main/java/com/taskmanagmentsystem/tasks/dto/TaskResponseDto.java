package com.taskmanagmentsystem.tasks.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.taskmanagmentsystem.tasks.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskResponseDto {
    private String title;
    private String description;
    @JsonProperty("task_status")
    private TaskStatus taskStatus;
}
