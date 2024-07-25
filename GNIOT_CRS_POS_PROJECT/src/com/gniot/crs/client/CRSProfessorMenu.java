package com.gniot.crs.client;

import java.util.List;
import java.util.Scanner;

import com.gniot.crs.bean.Course;
import com.gniot.crs.bean.Student;
import com.gniot.crs.business.ProfessorInterface;
import com.gniot.crs.business.ProfessorOperation;
import com.gniot.crs.dao.ProfessorDAOImpl;
import com.gniot.crs.dao.ProfessorDAOInterface;

/**
 * Class to display professor menu and handle interactions.
 */
public class CRSProfessorMenu {
	private static Scanner scanner = new Scanner(System.in);

	public static void displayProfessorMenu() {
		final String RED = "\u001B[31m"; // ANSI escape code for red text
		final String REDBG = "\u001B[41m"; // ANSI escape code for red text
		final String CYAN = "\u001B[96m";
		final String YELLOW = "\u001B[33m";
		final String RESET = "\u001B[0m";
		ProfessorInterface professorOps = new ProfessorOperation();

		ProfessorDAOInterface professorDAO = new ProfessorDAOImpl();
		while (true) {
			try {
				System.out.println("Professor Menu");
				System.out.println("1. Add Grade");
				System.out.println("2. View Enrolled Students");
				System.out.println("0. Logout");
				System.out.print("Enter your choice: ");
				int choice = scanner.nextInt();
				scanner.nextLine(); // Consume newline character

				switch (choice) {
				case 1: {
					int professorId = professorDAO.currentProfessorId();
					if (professorId == -1) {
						System.out.println(RED + "Error: Professor not found." + RESET);
						return;
					}
					// 1. Get courses taught by the professor
					List<Course> courses = professorDAO.getProfessorCourses(professorId);
					if (courses.isEmpty()) {
						System.out.println(YELLOW + "You are not assigned to any courses." + RESET);
						return;
					}

					System.out.println("Courses You Teach:");
					professorOps.printCoursesTable(courses);

					// 2. Get course ID input from user
					System.out.print(CYAN + "Enter the course ID: " + RESET);
					int courseId;
					try {
						courseId = Integer.parseInt(scanner.nextLine().trim());
					} catch (NumberFormatException e) {
						System.out.println(REDBG + "Invalid course ID. Please enter a number." + RESET);
						return;
					}

					// 3. Display enrolled students for the chosen course
					List<Student> students = professorDAO.getEnrolledStudentsInCourse(courseId);
					if (students.isEmpty()) {
						System.out.println(YELLOW + "No students are enrolled in this course." + RESET);
						return;
					}

					System.out.println("Enrolled Students:");
					professorOps.printStudentTable(students);

					// 4. Get student ID input from user
					System.out.print(CYAN + "Enter the student ID: " + RESET);
					int studentId;
					try {
						studentId = Integer.parseInt(scanner.nextLine().trim());

						// Check if the entered student ID is valid
						boolean studentExists = students.stream()
								.anyMatch(student -> student.getStudentId() == studentId);
						if (studentExists) {
							System.out.println(RED + "Error: Student not found in this course." + RESET);
							return;
						}
					} catch (NumberFormatException e) {
						System.out.println(REDBG + "Invalid student ID. Please enter a number." + RESET);
						return;
					}
					// 5. Get the grade and update the database
					System.out.print(CYAN + "Enter the grade (A+, A, A-, B+, B, B-, C+, C, C-, D+, D, F): " + RESET);
					String grade = scanner.nextLine().toUpperCase(); // Convert to uppercase for consistent checks

					// Grade format validation
					if (!grade.matches("[ABCDF][+-]?")) {
						System.out
								.println(REDBG + "Invalid grade format. Please enter a valid grade (A+, A, A-, etc.)." + RESET);
						return;
					}

					professorOps.addGrade(studentId, courseId, grade);
					break;
				}
				case 2:
					professorOps.viewEnrolledStudents();
					break;
				case 0:
					System.out.println("Logging out...");
					return;
				default:
					System.out.println("Invalid choice. Please try again.");
				}
			} catch (Exception e) {
				System.out.println("An unexpected error occurred: " + e.getMessage());
				e.printStackTrace();
				scanner.nextLine(); // Consume any remaining input to prevent loop issues
			}
		}
	}
}
