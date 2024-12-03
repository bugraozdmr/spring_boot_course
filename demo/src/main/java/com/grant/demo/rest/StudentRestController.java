package com.grant.demo.rest;

import com.grant.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    private final List<Student> students = new ArrayList<>();


    // define @PostConstruct to load the student data only once ...
    // Bu metot, Spring konteyneri tarafından sınıfın bir instance’ını oluşturduktan ve bağımlılık enjeksiyonlarını tamamladıktan sonra bir kez çağrılır.
    @PostConstruct
    public void loadData() {
        this.students.add(new Student("Grant","Wick"));
        this.students.add(new Student("Polat","Alemdar"));
        this.students.add(new Student("John","Smith"));
    }


    // "/students" endpoint

    // @RestController ile işaretlenmişse, bu sınıftaki her bir endpoint, varsayılan olarak JSON veya XML gibi bir formatta veri döndürür
    @GetMapping("/students")
    public List<Student> getStudents() {
        return this.students;
    }

    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId) {
        if (studentId < 0 || studentId >= students.size()) {
            throw new StudentNotFoundException("Student ID not found: " + studentId);
        }

        return this.students.get(studentId);
    }


}
