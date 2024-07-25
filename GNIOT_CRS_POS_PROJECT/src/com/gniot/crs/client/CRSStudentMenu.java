/**
 * 
 */
package com.gniot.crs.client;

import java.util.List;
import java.util.Scanner;

import com.gniot.crs.bean.Course;
import com.gniot.crs.business.StudentInterface;
import com.gniot.crs.business.StudentOperation;
import com.gniot.crs.dao.StudentDAOImpl;

/**
 * Represents the student menu in the course registration system.
 */
public class CRSStudentMenu {
	/**
	 * Displays the student menu and handles user input.
	 * 
	 * @param studentId The ID of the student currently logged in.
	 */
	private static Scanner scanner = new Scanner(System.in);

	public static void displayStudentMenu() { // Pass studentId
		StudentInterface studOps = new StudentOperation();

		StudentDAOImpl studentDAO = new StudentDAOImpl();

		int studentId = studentDAO.currentStudentId();

		final String RESET = "\u001B[0m";
		final String CYAN = "\u001B[96m";
		while (true) {
			System.out.println("Student Menu");
			System.out.println("1. Browse Catalog for Courses");
			System.out.println("2. Add Course");
			System.out.println("3. Remove Course");
			System.out.println("4. View Grades");
			System.out.println("5. Account Info");
			System.out.println("6. Payment");
			System.out.println("0. Logout");

			System.out.print("Enter your choice: ");
			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1:
				studOps.browseCatalogForCoures();
				break;
			case 2:
				studOps.addCourse();
				break;
			case 3: {
				// 1. Display enrolled courses
				List<Course> enrolledCourses = studentDAO.getEnrolledCourses(studentId);
				if (enrolledCourses.isEmpty()) {
					System.out.println("You are not enrolled in any courses.");
					return;
				}

				System.out.println("Enrolled Courses:");
				studOps.printHorizontalLine(90); // Adjust width as needed
				System.out.printf("| %-10s | %-25s | %-20s |%-25s |\n", "Course ID", "Course Name", "Professor Id",
						"Professor Name");
				studOps.printHorizontalLine(90);
				for (Course course : enrolledCourses) {
					System.out.printf("| %-10d | %-25s | %-20s |%-25s |\n", course.getCourse_id(),
							course.getCourseName(), course.getprofessorId(), course.getProfessorName());
					studOps.printHorizontalLine(90);
				}

				// 2. Get course ID input from user

				System.out.print(CYAN + "Enter the course ID you want to remove (or '0' to finish): " + RESET);
				String courseIdStr = scanner.nextLine();
				studOps.removeCourse(courseIdStr);
				break;
			}
			case 4:
				studOps.viewGrades();
				break;
			case 5:
				studOps.accountInfo();
				break;
			case 6:
				studOps.payment();
				break;
			case 0:
				return;
			default:
				System.out.println("Invalid choice. Please try again.");

			}
		}
	}

}
