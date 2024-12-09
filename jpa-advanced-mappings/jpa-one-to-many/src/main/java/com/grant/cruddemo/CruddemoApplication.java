package com.grant.cruddemo;

import com.grant.cruddemo.dao.AppDAO;
import com.grant.cruddemo.entity.Course;
import com.grant.cruddemo.entity.Instructor;
import com.grant.cruddemo.entity.InstructorDetail;
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
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {
			// createInstructor(appDAO);
			// findInstructor(appDAO);
			// deleteInstructor(appDAO);
			// findInstructorDetail(appDAO);
			// deleteInstructorDetail(appDAO);
			// createInstructorWithCourses(appDAO);
			// findInstructorWithCourses(appDAO);
			// findCoursesForInstructor(appDAO); // SORUNLU

			findInstructorWithCoursesJoinFetch(appDAO);

		};
	}

	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
		// fetch = LAZY
		int id=1;
		System.out.println("Finding instructor id: " + id);
		Instructor instructor = appDAO.findInstructorByIdJoinFetch(id);

		System.out.println("instructor: " + instructor);

		System.out.println("associated courses: " + instructor.getCourses());
		System.out.println("associated details: " + instructor.getInstructorDetail());
		System.out.println("Done");
	}

	private void findCoursesForInstructor(AppDAO appDAO) {
		int theId = 1;
		// find instructor
		System.out.println("Finding instructor id: " + theId);

		Instructor tempInstructor = appDAO.findInstructorById(theId);

		System.out.println("tempInstructor: " + tempInstructor);

		// find courses for instructor
		System.out.println("Finding courses for instructor id: " + theId);
		List<Course> courses = appDAO.findCoursesByInstructorId(theId);

		// associate the objects
		tempInstructor.setCourses(courses);

		System.out.println("the associated courses: " + tempInstructor.getCourses());

		System.out.println("Done!");
	}

	private void findInstructorWithCourses(AppDAO appDAO) {
		int id=1;
		System.out.println("Finding instructor id: " + id);
		Instructor instructor = appDAO.findInstructorById(id);
		System.out.println("instructor: " +instructor);
		System.out.println("associated courses: " +instructor.getCourses());
		System.out.println("Done");
	}

	private void createInstructorWithCourses(AppDAO appDAO) {
		Instructor tempInstructor =
				new Instructor("Susan","Pepper","polat@gmail.com");

		InstructorDetail instructorDetail =
				new InstructorDetail("https://www.youtube.com/@susan-f1b","Video Games");


		tempInstructor.setInstructorDetail(instructorDetail);

		Course course = new Course("Spring Boot Ultimate Course");
		Course course1 = new Course("Dotnet Ultimate Course");

		tempInstructor.add(course);
		tempInstructor.add(course1);

		// THIS will also save course
		// CascadeType.Persist
		System.out.println("Saving instructor: " + tempInstructor);
		System.out.println("The courses: " + tempInstructor.getCourses());
		appDAO.save(tempInstructor);
	}

	private void deleteInstructorDetail(AppDAO appDAO) {
		int id=3;
		System.out.println("Deleting instructor id: " + id);

		// eğer cascadeden remove'ı çıkarsan biri gider diğeri kalır
		appDAO.deleteInstructorDetailById(id);

		System.out.println("Done");
	}

	private void findInstructorDetail(AppDAO appDAO) {
		int id=2;
		InstructorDetail instructorDetail = appDAO.findInstructorDetailById(id);

		System.out.println("instructorDetail: " + instructorDetail);

		System.out.println("The associated instructor: " + instructorDetail.getInstructor());

		System.out.println("Done");
	}

	private void deleteInstructor(AppDAO appDAO) {
		int id=1;
		System.out.println("Deleting instructor id: " + id);

		// CASCADE deletes both
		appDAO.deleteInstructorById(id);
		System.out.println("Done");
	}

	private void findInstructor(AppDAO appDAO) {
		int id = 1;
		System.out.println("Finding instructor id: " + id);
		Instructor tempInstructor = appDAO.findInstructorById(id);

		// direkt cekme ve toplu cekme
		System.out.println("TempInstructor: " + tempInstructor);
		System.out.println("The associated instructorDetail only: " + tempInstructor.getInstructorDetail());
	}

	private void createInstructor(AppDAO appDAO) {
		/*
		Instructor tempInstructor =
				new Instructor("Grant","Wick","grant@gmail.com");

		InstructorDetail instructorDetail =
				new InstructorDetail("https://www.youtube.com/@lin1342-f1b","Gaming");
		*/

		Instructor tempInstructor =
				new Instructor("Polat","Wick","polat@gmail.com");

		// instructor save olurken aynı zamanda details'de save oluyor cascade sayesinde
		InstructorDetail instructorDetail =
				new InstructorDetail("https://www.youtube.com/@lin1342-f1b","Guitar");


		tempInstructor.setInstructorDetail(instructorDetail);

		// Because of CascadeType.ALL => this will ALSO save the details object
		System.out.println("Saving instructor: " + tempInstructor);
		appDAO.save(tempInstructor);

		System.out.printf("Done");
	}
}
