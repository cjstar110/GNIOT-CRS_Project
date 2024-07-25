package com.gniot.crs.business;

import java.util.*;
import com.gniot.crs.dao.ProfessorDAOInterface;
import com.gniot.crs.exception.*;
import com.gniot.crs.bean.Course;
import com.gniot.crs.bean.Student;
import com.gniot.crs.dao.ProfessorDAOImpl;

/**
 * Implementation of ProfessorInterface to handle professor operations.
 */
public class ProfessorOperation implements ProfessorInterface {
	private ProfessorDAOInterface professorDAO = new ProfessorDAOImpl();

	// ANSI escape codes for color formatting
	final String GREEN = "\u001B[32m"; // ANSI escape code for green text
	final String RED = "\u001B[31m"; // ANSI escape code for red text
	final String GREENBG = "\u001B[42m"; // ANSI escape code for green text
	final String REDBG = "\u001B[41m"; // ANSI escape code for red text
	final String CYAN = "\u001B[96m";
	final String YELLOW = "\u001B[33m";
	final String RESET = "\u001B[0m";
	public void addGrade(int studentId, int courseId, String grade) {
		try {
			

		

			
			professorDAO.addGrade(studentId, courseId, grade);
		} catch (GradeUpdateException | UnauthorizedGradeException e) {
			System.out.println(RED + e.getMessage() + RESET);
		}
	}

	public void viewEnrolledStudents() {
		try {
			int professorId = professorDAO.currentProfessorId(); // get the professor id of the logged-in professor
			if (professorId == -1) {
				System.out.println(RED + "Error: Professor not found." + RESET);
				return;
			}

			List<Student> students = professorDAO.getEnrolledStudents(professorId);
			if (students.isEmpty()) {
				System.out.println(YELLOW + "No students enrolled in your courses." + RESET);
			} else {
				System.out.println("Enrolled Students in Your Courses:");
				printStudentTable(students); // Assuming you have this utility method
			}
		} catch (EnrolledStudentsRetrievalException e) {
			System.out.println(RED + e.getMessage() + RESET);
		}
	}

	// Helper method to print the student details table
	public void printStudentTable(List<Student> students) {
		printHorizontalLine(138);
		System.out.printf("| %-5s | %-15s | %-15s | %-7s | %-4s | %-25s | %-15s | %-25s |%n", " ID   ", "  First Name",
				"  Last Name", "  Gender ", "  Age ", "Address", " Phone Number", " Email ID");
		printHorizontalLine(138);
		int index = 1; // Counter for student IDs in the table
		for (Student student : students) {
			System.out.printf("| %-6s | %-15s | %-15s | %-9s | %-6s | %-25s | %-15s | %-25s |%n", index,
					student.getFirstName(), student.getLastName(), student.getGender(), student.getAge(),
					student.getAddress(), student.getPhoneNumber(), student.getEmailId());
			index++;
		}
		printHorizontalLine(138);
	}

	private void printHorizontalLine(int... widths) {
		StringBuilder line = new StringBuilder("+");
		for (int width : widths) {
			for (int i = 0; i < width; i++) {
				line.append("-");
			}
			line.append("-+");
		}
		System.out.println(line);
	}

	public void printCoursesTable(List<Course> courses) {
		printHorizontalLine(50);
		System.out.printf("| %-10s | %-20s | %-10s |%n", " Course ID", "Course Name", " Course Code");
		printHorizontalLine(50);
		for (Course course : courses) {
			System.out.printf("| %-10s | %-20s | %-12s |%n", course.getCourse_id(), course.getCourseName(),
					course.getCourseCode());
		}
		printHorizontalLine(50);
	}
}