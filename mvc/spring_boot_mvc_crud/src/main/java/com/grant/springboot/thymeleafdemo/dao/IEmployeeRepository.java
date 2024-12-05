package com.grant.springboot.thymeleafdemo.dao;

import com.grant.springboot.thymeleafdemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// primary type integer
public interface IEmployeeRepository extends JpaRepository<Employee,Integer> {

    // JPA oto algılar
    public List<Employee> findAllByOrderByLastNameAsc();
}
