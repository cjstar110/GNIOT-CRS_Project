package com.gniot.crs.dao;

import java.util.List;

import com.gniot.crs.bean.Course;
import com.gniot.crs.bean.Professor;
import com.gniot.crs.exception.ProfessorRemovalException;

/**
 * The AdminDAOInterface defines the data access methods
 * that are required for admin operations in the course
 * registration system. It includes methods to manage courses,
 * professors, and student approvals.
 */
public interface AdminDAOInterface {
    void addCourseToCatalog(String courseName, String courseCode);

    void removeCourseFromCatalog(int courseId);

    boolean assignCourseToProfessor(int professorId, int courseId);

    boolean approveProfessor(int professorId);

    List<String> getPendingStudents();

    List<Course> getCourseCatalog();

    void approveStudent(String username);

    List<String> getPendingProfessors();

    List<Professor> getAllProfessors();

    boolean removeProfessor(int professorId) throws ProfessorRemovalException;

    boolean setBillAmount(int courseId, int newBillAmount);

    boolean removeProfessorFromCourse(int courseId);
}
