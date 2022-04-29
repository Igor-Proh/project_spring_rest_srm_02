package com.prokhnov.spring_rest.controller;

import com.prokhnov.spring_rest.entity.Employee;
import com.prokhnov.spring_rest.exception_handling.EmployeeIncorrectData;
import com.prokhnov.spring_rest.exception_handling.NotSuchEmployeeException;
import com.prokhnov.spring_rest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRestController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {

        return employeeService.getAllEmployees();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployeeById(@PathVariable int employeeId) {

        Employee employee = employeeService.getEmployee(employeeId);

        if (employee == null) {
            throw new NotSuchEmployeeException("There is no Employee id = " + employeeId);
        }

        return employee;
    }

}
