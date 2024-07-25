/**
 * 
 */
package com.gniot.crs.business;

import java.util.List;

import com.gniot.crs.bean.Course;
import com.gniot.crs.bean.Professor;

/**
 * 
 */

public interface AdminInterface {

	// Add course to catalog

	void addCourseToCatalog(int courseId, String courseName, String courseCode);

	// Remove course to catalog
	void removeCourseFromCatalog(int courseId);

	// Assign Course to Professor
	void assignCourseToProfessor(int professorId, int courseId);


	void removeProfessorFromCourse(int courseId);


	void setBillAmount(int courseId, int newBillAmount);
	// Approve Student

	void approveStudent(String studentUsername);
	// display course catalog
	public void displayCourseCatalog();

	public void listPendingStudents();


	void removeProfessor(int professorId);

	void listPendingProfessor();

	void approveProfessor(int professorId);

	void printCoursesTable(List<Course> courses);

	void printProfessorsTable(List<Professor> professors);








}
