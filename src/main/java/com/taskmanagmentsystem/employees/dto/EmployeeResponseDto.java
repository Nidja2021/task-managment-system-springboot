package com.taskmanagmentsystem.employees.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.taskmanagmentsystem.employees.EmployeeRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeResponseDto {
    private String firstname;
    private String lastname;
    private String email;
    @JsonProperty("employee_role")
    private EmployeeRole employeeRole;
}
