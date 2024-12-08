package com.grant.cruddemo;

import com.grant.cruddemo.dao.AppDAO;
import com.grant.cruddemo.entity.Instructor;
import com.grant.cruddemo.entity.InstructorDetail;
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
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {
			// createInstructor(appDAO);
			// findInstructor(appDAO);
			deleteInstructor(appDAO);
		};
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
