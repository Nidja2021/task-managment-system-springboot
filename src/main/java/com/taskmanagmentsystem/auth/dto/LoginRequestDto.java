package com.taskmanagmentsystem.auth.dto;

public record LoginRequestDto(
        String email,
        String password
) {
}
