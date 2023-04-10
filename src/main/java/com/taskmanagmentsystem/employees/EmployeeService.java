package com.taskmanagmentsystem.employees;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public ResponseEntity<List<Employee>> findAllEmployees() {
        return ResponseEntity.ok(employeeRepository.findAll());
    }
}
