package com.taskmanagmentsystem.employees;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EmployeeRole {
    EMPLOYEE("Employee"),
    MANAGER("Manager"),
    ADMIN("Admin");

    private final String displayValue;
}
