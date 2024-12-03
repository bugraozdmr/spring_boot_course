package com.grant.springboot.cruddemo.rest;

import com.grant.springboot.cruddemo.entity.Employee;
import com.grant.springboot.cruddemo.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    // private IEmployeeDAO _employeeDAO; direkt servis kullanmak istedik

    private IEmployeeService _employeeService;

    @Autowired
    public EmployeeRestController(IEmployeeService _employeeService) {
        this._employeeService = _employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> findAll() {
        return _employeeService.findAll();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId) {
        Employee employee = _employeeService.findById(employeeId);

        if (employee == null)
            throw new RuntimeException("Employee not found with id: " + employeeId);


        return employee;
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee) {
        // ID olmasa bile bu sekilde atamis olduk
        employee.setId(0);

        Employee dbEmployee = _employeeService.save(employee);

        return dbEmployee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {
        Employee dbEmployee = _employeeService.save(employee);

        return dbEmployee;
    }

    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId) {
        Employee tempEmployee = _employeeService.findById(employeeId);

        if (tempEmployee == null)
            throw new RuntimeException("Employee not found with id: " + employeeId);

        _employeeService.deleteById(employeeId);

        return "Deleted employee id: " + employeeId;
    }
}
