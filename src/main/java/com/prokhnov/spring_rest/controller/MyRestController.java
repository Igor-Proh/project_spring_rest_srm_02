package com.prokhnov.spring_rest.controller;

import com.prokhnov.spring_rest.entity.Employee;
import com.prokhnov.spring_rest.exception_handling.NotSuchEmployeeException;
import com.prokhnov.spring_rest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/employees")
    public Employee addNewEmployee(@RequestBody Employee employee){
        employee.setId(0);
        employeeService.saveEmployee(employee);
        return employee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee){

        employeeService.saveEmployee(employee);

        return employee;
    }

    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId){

        Employee employee = employeeService.getEmployee(employeeId);
        if (employee == null){
            throw new NotSuchEmployeeException("There is no employee with id = " + employeeId);
        }

        employeeService.deleteEmployee(employeeId);
        return "Delete employee with id - " + employeeId;
    }

}
