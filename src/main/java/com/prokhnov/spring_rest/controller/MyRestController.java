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

        List<Employee> employees = employeeService.getAllEmployees();

        return employees;
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployeeById(@PathVariable int employeeId) {

        Employee employee = employeeService.getEmployee(employeeId);

        if (employee == null) {
            throw new NotSuchEmployeeException("There is no Employee id = " + employeeId);
        }

        return employee;
    }

    @ExceptionHandler
    public ResponseEntity<EmployeeIncorrectData> handleException(
            NotSuchEmployeeException exception) {
        EmployeeIncorrectData data = new EmployeeIncorrectData();
        data.setInfo(exception.getMessage());

        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler
    public ResponseEntity<EmployeeIncorrectData> handleException(
            Exception exception) {
        EmployeeIncorrectData data = new EmployeeIncorrectData();
        data.setInfo(exception.getMessage());

        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);

    }


}
