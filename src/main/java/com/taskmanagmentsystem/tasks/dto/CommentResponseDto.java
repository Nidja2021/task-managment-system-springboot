package com.taskmanagmentsystem.tasks.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.taskmanagmentsystem.tasks.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentResponseDto {
    private String text;
    @JsonProperty(value = "task_id")
    private UUID taskId;
}
