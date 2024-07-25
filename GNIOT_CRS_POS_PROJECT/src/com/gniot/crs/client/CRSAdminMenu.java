
package com.gniot.crs.client;
import com.gniot.crs.business.AdminOperation;
import com.gniot.crs.dao.AdminDAOImpl;
import com.gniot.crs.dao.StudentDAOImpl;
import com.gniot.crs.exception.CourseNotFoundException;
import com.gniot.crs.exception.PendingProfessorsRetrievalException;
import com.gniot.crs.exception.ProfessorNotFoundException;

import java.util.List;
import java.util.Scanner;

import com.gniot.crs.bean.Course;
import com.gniot.crs.bean.Professor;
import com.gniot.crs.bean.User;
import com.gniot.crs.business.AdminInterface;
/**
 * Manages the menu for the CRS Admin and admin operations.
 */
public class CRSAdminMenu {
	

    private static Scanner scanner = new Scanner(System.in);

    public static void displayAdminMenu(List<User> users) {
    	final String REDBG = "\u001B[41m"; // ANSI escape code for red text
    	final String RESET = "\u001B[0m";
    	final String CYAN = "\u001B[96m";
    	final String YELLOW = "\u001B[33m";
        AdminInterface adminOps = new AdminOperation();

    	AdminDAOImpl adminDAO = new AdminDAOImpl();
    	StudentDAOImpl studentDAO = new StudentDAOImpl();
        while (true) {
            System.out.println("Admin Menu");
            System.out.println("1. Add course to catalog");
            System.out.println("2. Remove course from catalog");
            System.out.println("3. Set Bill Amount of Courses");
            System.out.println("4. Assign Course to Professor");
            System.out.println("5. Remove Professor From Course");
            System.out.println("6. Approve Professor");
            System.out.println("7. Remove Professor");
            System.out.println("8. Approve Student");
            System.out.println("9. View Catalog");
            System.out.println("0. Logout");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
            case 1:{
            	List<Course> courses = studentDAO.browseCatalogForCourses();
    			System.out.println("Already Added Courses:");
    			adminOps.printCoursesTable(courses);
    			System.out.print("Enter Course ID: ");
    			int courseId = scanner.nextInt();
    			scanner.nextLine();
    			System.out.println("Enter Course Name: ");
    			String courseName = scanner.nextLine();
    			System.out.println("Enter Course Code: ");
    			String courseCode = scanner.nextLine();
    			 adminOps.addCourseToCatalog(courseId, courseName, courseCode);
                
                break;}
            case 2:{
            	List<Course> courses = studentDAO.browseCatalogForCourses();
    			if (courses.isEmpty()) {
    				System.out.println(YELLOW + "No courses found." + RESET);
    				return;
    			}

    			System.out.println("Available Courses:");
    			adminOps.printCoursesTable(courses);
    			System.out.print("Enter Course id to remove from catalog: ");
    			int courseId = scanner.nextInt();
                adminOps.removeCourseFromCatalog(courseId);
                break;}
            case 3:{
            	// 1. Fetch and display all courses
        		List<Course> courses = studentDAO.browseCatalogForCourses();
        		if (courses.isEmpty()) {
        			System.out.println(YELLOW + "No courses found." + RESET);
        			return;
        		}

        		System.out.println("Available Courses:");
        		adminOps.printCoursesTable(courses);

        		System.out.print(CYAN + "Enter the course ID: " + RESET);
        		int courseId;
        		try {
        			courseId = Integer.parseInt(scanner.nextLine().trim());
        		} catch (NumberFormatException e) {
        			System.out.println(REDBG + "Invalid course ID. Please enter a number." + RESET);
        			return;
        		}
        		System.out.print(CYAN + "Enter the new bill amount: ₹" + RESET);
        		int newBillAmount;
        		try {
        			newBillAmount = Integer.parseInt(scanner.nextLine().trim());
        		} catch (NumberFormatException e) {
        			System.out.println(REDBG + "Invalid bill amount. Please enter a number." + RESET);
        			return;
        		}
                adminOps.setBillAmount(courseId,newBillAmount);;
                break;}
            case 4:{
            	// 1. Fetch and display all professors
    			List<Professor> professors = adminDAO.getAllProfessors();
    			if (professors.isEmpty()) {
    				throw new ProfessorNotFoundException("No professors found.");
    			}

    			System.out.println("Available Professors:");
    			adminOps.printProfessorsTable(professors);
    			// 2. Get professor and course IDs
    			System.out.print(CYAN + "Enter the professor ID: " + RESET);
    			int professorId = scanner.nextInt();
    			scanner.nextLine(); // Consume newline

    			// 3. Fetch and display all courses
    			List<Course> courses = adminDAO.getCourseCatalog();
    			if (courses.isEmpty()) {
    				throw new CourseNotFoundException("No courses found.");
    			}

    			System.out.println("\nAvailable Courses:");
    			adminOps.printCoursesTable(courses);

    			System.out.print(CYAN + "Enter the course ID: " + RESET);
    			int courseId = scanner.nextInt();
    			scanner.nextLine(); // Consume newline
                adminOps.assignCourseToProfessor(professorId, courseId);
                break;}
            case 5:{
            	List<Course> courses = adminDAO.getCourseCatalog();
        		if (courses.isEmpty()) {
        			System.out.println(YELLOW + "No c found." + RESET);
        			return;
        		}
        		System.out.println("Available Courses:");
        		adminOps.displayCourseCatalog();
        		System.out.print(CYAN + "Enter the Course ID from where to remove Professor: " + RESET);
        		int courseId = 0;
        		try {
        			courseId = Integer.parseInt(scanner.nextLine().trim());
        		} catch (NumberFormatException e) {
        			System.out.println(REDBG + "Invalid Course ID. Please enter a number." + RESET);
        			return; // Exit the method if the ID is invalid
        		}
                adminOps.removeProfessorFromCourse(courseId);;
                break;}
            case 6:{
            	List<String> pendingProfessors = adminDAO.getPendingProfessor();
    			if (pendingProfessors.isEmpty()) {
    				throw new PendingProfessorsRetrievalException("There are no pending professors to approve.");
    			}

    			// Display pending professors
    			for (String professorInfo : pendingProfessors) {
    				System.out.println(professorInfo);
    			}

    			// Get professor ID input from user
    			System.out.print(CYAN + "Enter the user ID of the professor to approve: " + RESET);
    			int professorId;
    			try {
    				professorId = Integer.parseInt(scanner.nextLine().trim());
    			} catch (NumberFormatException e) {
    				System.out.println(REDBG + "Invalid input. Please enter a number." + RESET);
    				return; // Exit the method if the input is invalid
    			}
                adminOps.approveProfessor(professorId);
                break;}
            case 7:{List<Professor> professors = adminDAO.getAllProfessors();
    		if (professors.isEmpty()) {
    			System.out.println(YELLOW + "No professors found." + RESET);
    			return;
    		}

    		System.out.println("Available Professors:");
    		adminOps.printProfessorsTable(professors);

    		// 2. Get professor ID to remove
    		System.out.print(CYAN + "Enter the professor ID to remove: " + RESET);
    		int professorId = 0;
    		try {
    			professorId = Integer.parseInt(scanner.nextLine().trim());
    		} catch (NumberFormatException e) {
    			System.out.println(REDBG + "Invalid professor ID. Please enter a number." + RESET);
    			return; // Exit the method if the ID is invalid
    		}
                adminOps.removeProfessor(professorId);
                break;}
            case 8:
            	adminOps.listPendingStudents();
                System.out.print("Enter the username of the student to approve: ");
                String studentUsername = scanner.nextLine();
                adminOps.approveStudent(studentUsername);
                break;
  
            case 9:
            	adminOps.displayCourseCatalog();
            	break;
            case 0:
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
