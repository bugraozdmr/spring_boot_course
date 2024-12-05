package com.grant.springboot.thymeleafdemo.controller;

import com.grant.springboot.thymeleafdemo.dao.EmployeeService;
import com.grant.springboot.thymeleafdemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService _employeeService;

    @Autowired // zaten bir tane constructor bulundugu icin oto spring algilar zaten ondan optional Autowired
    public EmployeeController(EmployeeService _employeeService) {
        this._employeeService = _employeeService;
    }

    // poma thymeleaf eklemeyi unutma
    @GetMapping("/list")
    public String listEmployees(Model model) {
        List<Employee> employeeList = _employeeService.findAll();

        model.addAttribute("employees",employeeList);

        return "employees/list-employees";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        Employee employee = new Employee();

        model.addAttribute("employee" , employee);

        return "employees/employee-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") int id,Model model) {
        Employee employee = _employeeService.findById(id);

        model.addAttribute("employee" , employee);

        return "employees/employee-form";
    }

    // data binding
    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        _employeeService.save(employee);

        // redirect to prevent duplicate submissions
        return "redirect:/employees/list";
    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("employeeId") int id) {
        _employeeService.deleteById(id);

        // redirect to prevent duplicate submissions
        return "redirect:/employees/list";
    }
}
