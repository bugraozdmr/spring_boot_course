package com.grant.cruddemo;

import com.grant.cruddemo.dao.IStudentDAO;
import com.grant.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(IStudentDAO studentDAO) {
		return runner -> {
			createStudent(studentDAO);
		};
	}

	private void createStudent(IStudentDAO studentDAO) {
		// create student object
		System.out.println("Creating new student object ...");
		Student tempStudent = new Student("Paul","Doe","paul@grant.com");

		// save the student object
		System.out.println("Saving the student ...");
		studentDAO.save(tempStudent);

		// display id of the saved student
		System.out.println("Saved student. Generated id: " + tempStudent.getId());
	}
}
/*
* HikariPool-1 - Start completed. varsa connection success demek mysql
* */