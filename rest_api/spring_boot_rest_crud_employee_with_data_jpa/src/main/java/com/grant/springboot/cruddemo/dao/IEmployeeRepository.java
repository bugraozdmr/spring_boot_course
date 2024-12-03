package com.grant.springboot.cruddemo.dao;

import com.grant.springboot.cruddemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

// primary type integer
public interface IEmployeeRepository extends JpaRepository<Employee,Integer> {
}
