package com.gniot.crs.business;

import com.gniot.crs.bean.Course;
import com.gniot.crs.bean.Professor;
import com.gniot.crs.dao.AdminDAOInterface;
import com.gniot.crs.exception.*;
import com.gniot.crs.dao.AdminDAOImpl;
import java.util.List;

public class AdminOperation implements AdminInterface {
	final String GREENBG = "\u001B[42m"; // ANSI escape code for green text
	final String REDBG = "\u001B[41m"; // ANSI escape code for red text
	final String RESET = "\u001B[0m";
	final String CYAN = "\u001B[96m";
	final String GREEN = "\u001B[32m"; // ANSI escape code for green text
	final String RED = "\u001B[31m"; // ANSI escape code for red text

	final String YELLOW = "\u001B[33m";
	private AdminDAOInterface adminDAO;

	public AdminOperation() {
		this.adminDAO = new AdminDAOImpl();
	}

	@Override
	public void addCourseToCatalog(int courseId,String courseName,String courseCode) {
		try {
		
			adminDAO.addCourseToCatalog(courseId, courseName, courseCode);
			System.out.println("Course " + courseName + " added to catalog.");
		} catch (CourseAdditionException e) {
			System.out.println(RED + e.getMessage() + RESET);
		}
	}

	@Override
	public void removeCourseFromCatalog(int courseId) {
		try {
			
			adminDAO.removeCourseFromCatalog(courseId);
			System.out.println("Course " + courseId + " removed from catalog.");
		} catch (CourseRemovalException e) {
			System.out.println(RED + e.getMessage() + RESET);
		}
	}

	public void setBillAmount(int courseId, int newBillAmount) {
				


		// 3. Update bill amount
		try {
			if (adminDAO.setBillAmount(courseId, newBillAmount)) {
				System.out.println(GREEN + "Bill amount updated successfully for course " + courseId + RESET);
			}
		} catch (CourseNotFoundException e) {
			System.out.println(RED + e.getMessage() + RESET);
		} catch (BillAmountUpdateException e) {
			System.out.println(RED + e.getMessage() + RESET);
		}
	}

	public void assignCourseToProfessor(int professorId, int courseId) {
		try {
			

			// 4. Assign course to professor
			adminDAO.assignCourseToProfessor(professorId, courseId);
			System.out.println(GREEN + "Course assigned to professor successfully." + RESET);

		} catch (ProfessorNotFoundException | CourseNotFoundException | CourseAssignmentException e) {
			System.out.println(RED + e.getMessage() + RESET);
		} catch (NumberFormatException e) { // Handle wrong input exception
			System.out.println(REDBG + "Invalid input. Please enter a number." + RESET);
		}
	}
	public void removeProfessorFromCourse(int courseId) {
		
//		
		// 3. Remove professor (rest of the logic remains the same)
		try {
			if (adminDAO.removeProfessorFromCourse( courseId)) {
				System.out.println(GREEN + "Professor with ID  has been removed." + RESET);
			} else {
				System.out.println(RED + "Failed to remove professor. Professor not found." + RESET);

			}
		} catch (RemoveProfessorFromCourseException e) {
		    System.out.println(RED + e.getMessage() + RESET);
		    // ... (you can add additional handling here, like logging the error) ...
		}
		
	}

	public void printProfessorsTable(List<Professor> professors) {
		printHorizontalLine(142);
		System.out.printf("| %-10s | %-15s | %-15s | %-7s | %-4s | %-25s | %-15s | %-25s |%n", "Professor ID",
				" First Name ", " Last Name", " Gender ", "  Age", " Address", " Phone Number", "Email ID");
		printHorizontalLine(142);
		for (Professor prof : professors) {
			System.out.printf("| %-12s | %-15s | %-15s | %-8s | %-5s | %-25s | %-15s | %-25s |%n",
					prof.getProfessorId(), prof.getFirstName(), prof.getLastName(), prof.getGender(), prof.getAge(),
					prof.getAddress(), prof.getPhoneNumber(), prof.getEmailId());
			printHorizontalLine(142);
		}
	}

	public void printCoursesTable(List<Course> courses) {
		printHorizontalLine(55);
		System.out.printf("| %-10s | %-15s | %-10s| %-10s|%n", "Course ID", " Course Name", " Course Code",
				"Bill Amount");
		printHorizontalLine(55);
		for (Course course : courses) {
			System.out.printf("| %-10s | %-15s | %-12s| %-10s|%n", course.getCourse_id(), course.getCourseName(),
					course.getCourseCode(), course.getBillAmount());
		}
		printHorizontalLine(55);
	}

	public void approveProfessor(int professorId) {
		try {
			

			// Approve the professor
			adminDAO.approveProfessor(professorId);
			System.out.println(GREEN + "Professor with ID " + professorId + " has been approved." + RESET);
		} catch (PendingProfessorsRetrievalException | ProfessorNotFoundException e) {
			// Handle exceptions
			System.out.println(RED + e.getMessage() + RESET);
			// You might want to add more specific handling or logging here
		}
	}

	public void removeProfessor(int professorId) {
		// 1. Fetch and display all professors
		

		// 3. Remove professor (rest of the logic remains the same)
		try {
			if (adminDAO.removeProfessor(professorId)) {
				System.out.println(GREEN + "Professor with ID " + professorId + " has been removed." + RESET);
			} else {
				System.out.println(RED + "Failed to remove professor. Professor not found." + RESET);

			}
		} catch (ProfessorRemovalException e) {
			System.out.println(RED + e.getMessage() + RESET);
			// ... (optional: handle the error differently based on its type)
		}
	}

	@Override
	public void approveStudent(String Username) {
		try {
			adminDAO.approveStudent(Username);
			System.out.println("Student " + Username + " has been approved.");
		} catch (StudentApprovalException | StudentNotFoundException e) { // Catch both exceptions
			System.out.println(RED + e.getMessage() + RESET);
		}
	}

	@Override
	public void displayCourseCatalog() {
		List<Course> courses = adminDAO.getCourseCatalog();
		System.out.println("Course Catalog:");
		printHorizontalLine(109);
		System.out.printf("| %-10s | %-25s | %-25s | %-12s |%-25s |\n", "Course ID", "Course Code", "Course Name",
				"Professor Id","Professor Name");
		printHorizontalLine(109);

		for (Course course : courses) {
			System.out.printf("| %-10s | %-25s | %-25s | %-12s |%-25s |\n", course.getCourse_id(), course.getCourseCode(),
					course.getCourseName(), course.getprofessorId(),course.getProfessorName());
			printHorizontalLine(109);
		}
	}

	@Override
	public void listPendingStudents() {
		try {
			List<String> pendingStudents = adminDAO.getPendingStudents();
			System.out.println("Pending Student Approvals:");
			printHorizontalLine(25);
			System.out.printf("| %-5s | %-10s |\n", "User ID", "User Name");
			printHorizontalLine(25);
			for (String user : pendingStudents) {
				System.out.println("" + user);
				printHorizontalLine(25);
			}
		} catch (PendingStudentsRetrievalException e) {
			System.out.println(RED + e.getMessage() + RESET);
		}

	}

	@Override
	public void listPendingProfessor() {
		List<String> pendingProfessor = adminDAO.getPendingProfessor();
		System.out.println("Pending Professor Approvals:");
		printHorizontalLine(25);
		System.out.printf("| %-5s | %-10s |\n", "User ID", "User Name");
		printHorizontalLine(25);
		for (String user : pendingProfessor) {
			System.out.println("" + user);
			printHorizontalLine(25);
		}

	}

	public void displayApprovedStudents() {
		System.out.println("Approved Students:");
//	        for (User student : approvedStudents) {
//	            System.out.println("Username: " + student.getUsername() + ", Role: " + student.getRole());
		// Add more details as needed (e.g., student ID, email, etc.)
//	        }
		// TODO Auto-generated method stub

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
}
