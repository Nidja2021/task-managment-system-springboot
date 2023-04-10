package com.taskmanagmentsystem.employees;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    @GetMapping
    public String getAllEmployees(){
        return "all employees";
    }
}
