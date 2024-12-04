package com.grant.springboot.thymeleafdemo.controller;

import com.grant.springboot.thymeleafdemo.model.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StudentController {

    // application.properties içinden çekiyor bu şekilde listeye atıyor
    @Value("${countries}")
    private List<String> _countries;

    @Value("${languages}")
    private List<String> _languages;

    @Value("${systems}")
    private List<String> _systems;

    // Metod String döndüğünde, bu değer bir view name olarak değerlendirilir.
    @GetMapping("/showStudentForm")
    public String showForm(Model model) {

        Student student = new Student();

        // have to same name in the html
        model.addAttribute("student", student);

        model.addAttribute("countries",_countries);

        model.addAttribute("languages",_languages);

        model.addAttribute("systems",_systems);

        return "student-form";
    }

    @PostMapping("/processStudentForm")
    public String processStudentForm(@ModelAttribute("student") Student student) {
        System.out.println("student: " + student.getFirstName() + " " + student.getLastName());

        return "student-confirmation";
    }
}
