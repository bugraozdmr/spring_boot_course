package com.grant.springboot.cruddemo.dao;

import com.grant.springboot.cruddemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

// primary type integer
@RepositoryRestResource(path = "members")       // magic-api/members
public interface IEmployeeRepository extends JpaRepository<Employee,Integer> {
}

/*
* hateos kullanarak rest işlemlerinin hepsini hallediyor bu hiçbir şey yazmana gerek yok id'e gore filtreleme pagination hersey var -- kendisi 's' atar bu kadar
* http://localhost:8080/employees/1
*
* spring.data.rest.base-path=/magic-api -- bu genel yol oluyor
* ekleyince artık
* /api/employees
*
* genel son
* http://localhost:8080/magic-api/members
* http://localhost:8080/magic-api/members?page=0&size=2
* spring.data.rest.default-page-size=15 # properties
* */
