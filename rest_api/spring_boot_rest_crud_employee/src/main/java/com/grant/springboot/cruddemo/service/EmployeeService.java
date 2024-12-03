package com.grant.springboot.cruddemo.service;

import com.grant.springboot.cruddemo.dao.EmployeeDAO;
import com.grant.springboot.cruddemo.entity.Employee;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService implements IEmployeeService{

    private EmployeeDAO _employeeDAO;

    public EmployeeService(EmployeeDAO _employeeDAO) {
        this._employeeDAO = _employeeDAO;
    }

    @Override
    public List<Employee> findAll() {
        return _employeeDAO.findAll();
    }

    @Override
    public Employee findById(int id) {
        return _employeeDAO.findById(id);
    }

    @Transactional
    @Override
    public Employee save(Employee employee) {
        return _employeeDAO.save(employee);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        _employeeDAO.deleteById(id);
    }
}
