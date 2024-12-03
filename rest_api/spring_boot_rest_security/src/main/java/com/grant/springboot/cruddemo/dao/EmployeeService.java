package com.grant.springboot.cruddemo.dao;

import com.grant.springboot.cruddemo.entity.Employee;
import com.grant.springboot.cruddemo.service.IEmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements IEmployeeService {

    private IEmployeeRepository _employeeRepository;

    public EmployeeService(IEmployeeRepository _employeeRepository) {
        this._employeeRepository = _employeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return _employeeRepository.findAll();
    }

    @Override
    public Employee findById(int id) {
        Optional<Employee> result = _employeeRepository.findById(id);

        Employee employee = null;

        if (result.isPresent()){
            employee = result.get();
        }
        else {
            throw new RuntimeException("Employee with id: " + id + " could not found");
        }


        return employee;
    }

    @Transactional
    @Override
    public Employee save(Employee employee) {
        return _employeeRepository.save(employee);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        _employeeRepository.deleteById(id);
    }
}
