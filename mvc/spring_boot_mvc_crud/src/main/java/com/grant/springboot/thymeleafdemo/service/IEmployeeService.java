package com.grant.springboot.thymeleafdemo.service;

import com.grant.springboot.thymeleafdemo.entity.Employee;

import java.util.List;

public interface IEmployeeService {
    List<Employee> findAll();

    Employee findById(int id);

    Employee save(Employee employee);

    void deleteById(int id);
}
