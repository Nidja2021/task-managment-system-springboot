package com.taskmanagmentsystem.employees;

import com.taskmanagmentsystem.employees.dto.EmployeeResponseDto;
import com.taskmanagmentsystem.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public ResponseEntity<List<EmployeeResponseDto>> findAllEmployees() {
        List<EmployeeResponseDto> employeeResponseDtoList = employeeRepository.findAll()
                .stream().map(employee -> EmployeeResponseDto.builder()
                        .firstname(employee.getFirstname())
                        .lastname(employee.getLastname())
                        .email(employee.getEmail())
                        .employeeRole(employee.getEmployeeRole())
                        .build()).collect(Collectors.toList());

        return ResponseEntity.ok(employeeResponseDtoList);
    }

    public ResponseEntity<EmployeeResponseDto> findEmployeeById(UUID employeeId) {
        var employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new NotFoundException("Employee does not exists."));
        EmployeeResponseDto employeeResponseDto = convertToEmployeeResponseDto(employee);
        return ResponseEntity.ok(employeeResponseDto);
    }

    private EmployeeResponseDto convertToEmployeeResponseDto(Employee employee) {
        return EmployeeResponseDto.builder()
                .firstname(employee.getFirstname())
                .lastname(employee.getLastname())
                .email(employee.getEmail())
                .employeeRole(employee.getEmployeeRole())
                .build();
    }
}
