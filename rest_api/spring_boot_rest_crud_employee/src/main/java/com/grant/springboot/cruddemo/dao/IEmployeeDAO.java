package com.grant.springboot.cruddemo.dao;

import com.grant.springboot.cruddemo.entity.Employee;

import java.util.List;

public interface IEmployeeDAO {
    List<Employee> findAll();

    Employee findById(int id);

    Employee save(Employee employee);

    void deleteById(int id);
}
