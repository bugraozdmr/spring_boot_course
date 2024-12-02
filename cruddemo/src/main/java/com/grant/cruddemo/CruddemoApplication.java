package com.grant.cruddemo;

import com.grant.cruddemo.dao.IStudentDAO;
import com.grant.cruddemo.dao.StudentDAO;
import com.grant.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;


@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(IStudentDAO studentDAO) {
		return runner -> {
			// createStudent(studentDAO);

			createMultipleStudents(studentDAO);

			// readStudent(studentDAO);

			// queryForStudents(studentDAO);

			// queryForStudentsByLastName(studentDAO);

			// updateStudent(studentDAO);

			// deleteStudent(studentDAO);

			// deleteAllStudents(studentDAO);
		};
	}

	public void deleteAllStudents(IStudentDAO studentDAO) {
		System.out.println("Deleting all students ...");
		int numRowsDeleted = studentDAO.deleteAll();
		System.out.println("Deleted row count: " + numRowsDeleted);
	}

	public void deleteStudent(IStudentDAO studentDAO) {
		int studentId=2;
		System.out.println("Deleting student id: " + studentId);
		studentDAO.delete(studentId);
	}

	public void updateStudent(IStudentDAO studentDAO) {
		// retrieve student base on the id: primary key
		int studentId = 1;
		System.out.println("Getting student with id: " + studentId);
		Student myStudent = studentDAO.findById(studentId);

		// change first name to "Grant"
		System.out.println("Updating student ...");
		myStudent.setFirstName("Grant13");

		// Update the student
		studentDAO.update(myStudent);

		// display the updated student
		System.out.println("Updated student: " + myStudent);
	}

	public void queryForStudentsByLastName(IStudentDAO studentDAO) {
		// get a list of students
		List<Student> theStudents = studentDAO.findByLastName("Wick");

		// display list of students
		for (Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

	public void queryForStudents(IStudentDAO studentDAO) {
		// get a list of students
		List<Student> theStudents = studentDAO.findAll();

		// display list of students
		for (Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

	public void readStudent(IStudentDAO studentDAO) {
		// create a student object
		System.out.println("Creating new student object ...");
		Student tempStudent = new Student("Daffy","Duck","daffy@luv2code.com");

		// save the student
		System.out.println("Saving the student ...");
		studentDAO.save(tempStudent);

		// display id of the saved student
		int theId = tempStudent.getId();
		System.out.println("Saved student. Generated id: " + theId);

		// retrieve student based on the id: primary key
		System.out.println("Retrieving student with id: " + theId);
		Student myStudent = studentDAO.findById(theId);

		// display student
		System.out.printf("Found the student: " + myStudent);
	}

	private void createMultipleStudents(IStudentDAO studentDAO) {
		// create multiple students
		System.out.println("Creating 3 student objects ...");
		Student tempStudent1 = new Student("Grant","Alemdar","grant@grant.com");
		Student tempStudent2 = new Student("Kemal","Wick","kemal@grant.com");
		Student tempStudent3 = new Student("Polat","Rivia","polat@grant.com");

		// save the student objects
		System.out.println("Saving the students ...");
		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);
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
* ALTER TABLE student_tracker.student AUTO_INCREMENT=3000  -- 3000den baslar
* TRUNCATE student_tracker.student -- bunu sıfırlar ancak tum verilerde duser
* JPA is more modern https://www.youtube.com/watch?v=8SGI_XS5OPw
* https://chatgpt.com/share/674dcb43-0e44-8002-933e-fbeccd19b71a
* */