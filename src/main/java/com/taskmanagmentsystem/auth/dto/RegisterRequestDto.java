package com.taskmanagmentsystem.auth.dto;

public record RegisterRequestDto(
        String firstname,
        String lastname,
        String email,
        String password
) {}
