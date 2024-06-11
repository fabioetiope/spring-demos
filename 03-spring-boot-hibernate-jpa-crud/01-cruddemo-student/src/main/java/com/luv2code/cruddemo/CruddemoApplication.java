package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.StudentDAO;
import com.luv2code.cruddemo.entity.Student;
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
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO){
		return runner -> {
			createMultipleStudents(studentDAO);
			//readStudent(studentDAO);
			//queryForStudents(studentDAO);
			//queryForStudentsByLastName(studentDAO);
			//updateStudent(studentDAO);
			//deleteStudent(studentDAO);
			//deleteAll(studentDAO);
		};
	}

	private void deleteAll(StudentDAO studentDAO) {
		int i = studentDAO.deleteAll();
		System.out.println("Rows deleted: " + i);
	}

	private void deleteStudent(StudentDAO studentDAO) {
		studentDAO.delete(3005);
		System.out.println("deleted student 3005");
	}

	private void updateStudent(StudentDAO studentDAO) {
		int studentID = 1;
		System.out.println("Getting student with id: " + studentID);
		Student myStudent = studentDAO.findById(3003);

		System.out.println("updating first name");
		myStudent.setFirstName("Scooby");

		studentDAO.update(myStudent);

		System.out.println("Updated: " + myStudent);
	}

	private void queryForStudentsByLastName(StudentDAO studentDAO) {
		List<Student> students = studentDAO.findByLastName("Doe");
		for (Student s : students){
			System.out.println(s);
		}
	}

	private void queryForStudents(StudentDAO studentDAO) {
		for (Student s : studentDAO.findAll()){
			System.out.println(s);
		}
	}

	private void readStudent(StudentDAO studentDAO) {
		System.out.println("Creating new student object...");
		Student tempStudent = new Student("Fabio", "E", "fabioe@gmail.com");

		System.out.println("Saving the student...");
		studentDAO.save(tempStudent);

		int id = tempStudent.getId();
		System.out.println("Saved student, id:" + id);

		System.out.println("Retriving student by id");
		Student myStudent = studentDAO.findById(id);

		System.out.println("Found student: " + myStudent.toString());
	}

	private void createStudent(StudentDAO studentDAO) {
		System.out.println("Creating new student object...");
		Student tempStudent = new Student("Paul", "Doe", "pauldoe@gmail.com");

		System.out.println("Saving the student...");
		studentDAO.save(tempStudent);

		System.out.println("Saved student. Generated id: " + tempStudent.getId());
	}

	private void createMultipleStudents(StudentDAO studentDAO){
		System.out.println("Creating 3 students objects...");
		Student tempStudent1 = new Student("John", "Doe", "johndoe@gmail.com");
		Student tempStudent2 = new Student("Mary", "Public", "marypublic@gmail.com");
		Student tempStudent3 = new Student("Bonita", "Applebum", "bonitaapplebum@gmail.com");

		System.out.println("Saving the students...");
		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);

	}
}
