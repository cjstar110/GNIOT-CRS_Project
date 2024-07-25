package com.gniot.crs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.gniot.crs.bean.Course;
import com.gniot.crs.bean.Professor;
import com.gniot.crs.constant.SQLConstant;
import com.gniot.crs.exception.BillAmountUpdateException;
import com.gniot.crs.exception.CourseAdditionException;
import com.gniot.crs.exception.CourseAssignmentException;
import com.gniot.crs.exception.CourseCatalogRetrievalException;
import com.gniot.crs.exception.CourseNotFoundException;
import com.gniot.crs.exception.CourseRemovalException;
import com.gniot.crs.exception.PendingProfessorsRetrievalException;
import com.gniot.crs.exception.ProfessorNotFoundException;
import com.gniot.crs.exception.ProfessorRemovalException;
import com.gniot.crs.exception.RemoveProfessorFromCourseException;
import com.gniot.crs.utils.DBUtils;

/**
 * The AdminDAOImpl class implements the AdminDAOInterface. It provides the SQL
 * commands for each method to interact with the database for managing courses,
 * professors, and student approvals.
 */
@Repository
public class AdminDAOImpl implements AdminDAOInterface {

    @Override
    public void addCourseToCatalog( String courseName, String courseCode) {
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQLConstant.INSERT_COURSES)) {
            stmt.setString(1, courseName);
            stmt.setString(2, courseCode);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Course added successfully to catalog.");
            } else {
                throw new CourseAdditionException("Failed to add course to catalog.");
            }
        } catch (SQLException ex) {
            throw new CourseAdditionException("Error adding course to catalog", ex);
        }
    }

    @Override
    public void removeCourseFromCatalog(int courseId) {
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQLConstant.REMOVE_COURSES)) {
            stmt.setInt(1, courseId);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted == 0) {
                throw new CourseRemovalException("Course with ID " + courseId + " not found in the catalog.");
            } else {
                System.out.println("Course removed successfully from catalog.");
            }
        } catch (SQLException ex) {
            throw new CourseRemovalException("Error removing course from catalog", ex);
        }
    }

    @Override
    public boolean assignCourseToProfessor(int professorId, int courseId) {
        try (Connection conn = DBUtils.getConnection()) {
            if (!professorExists(conn, professorId)) {
                throw new ProfessorNotFoundException("Professor not found with ID " + professorId);
            }
            if (!courseExists(conn, courseId)) {
                throw new CourseNotFoundException("Course not found with ID " + courseId);
            }
            try (PreparedStatement pstmt = conn.prepareStatement(SQLConstant.ASSIGN_COURSE)) {
                pstmt.setInt(1, professorId);
                pstmt.setInt(2, professorId);
                pstmt.setInt(3, courseId);
                int rowsUpdated = pstmt.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Course assigned successfully to professor.");
                    return true;
                } else {
                    throw new CourseAssignmentException("Failed to assign course to professor.");
                }
            }
        } catch (SQLException ex) {
            throw new CourseAssignmentException("Error assigning course to professor", ex);
        }
    }

    @Override
    public boolean approveProfessor(int professorId) {
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQLConstant.APPROVE_PROFESSOR)) {
            pstmt.setInt(1, professorId);
            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Professor approved successfully.");
                return true;
            } else {
                throw new ProfessorNotFoundException("Professor with ID " + professorId + " not found.");
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error approving professor", ex);
        }
    }

    @Override
    public List<String> getPendingStudents() {
        List<String> pendingStudents = new ArrayList<>();
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQLConstant.GET_PENDING_STUDENTS);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                pendingStudents.add(rs.getString("username"));
            }
        } catch (SQLException ex) {
            throw new CourseCatalogRetrievalException("Error fetching pending students", ex);
        }
        return pendingStudents;
    }

    @Override
    public List<String> getPendingProfessors() {
        List<String> pendingProfessors = new ArrayList<>();
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQLConstant.GET_PENDING_PROFESSOR);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                pendingProfessors.add(rs.getString("username"));
            }
        } catch (SQLException ex) {
            throw new PendingProfessorsRetrievalException("Error fetching pending professors", ex);
        }
        return pendingProfessors;
    }

    @Override
    public List<Course> getCourseCatalog() {
        List<Course> courses = new ArrayList<>();
        try (Connection conn = DBUtils.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQLConstant.GET_COURSE_CATALOG)) {
            while (rs.next()) {
                Course course = new Course(
                        rs.getInt("course_id"),
                        rs.getString("course_code"),
                        rs.getString("course_name"),
                        rs.getString("professor_id"),
                        rs.getString("professor_name"),
                        rs.getInt("bill_amount"),
                        rs.getInt("capacity"),
                        rs.getInt("currentEnrollment")
                );
                courses.add(course);
            }
        } catch (SQLException ex) {
            throw new CourseCatalogRetrievalException("Error retrieving course catalog", ex);
        }
        return courses;
    }

    @Override
    public void approveStudent(String username) {
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQLConstant.APPROVE_STUDENT)) {
            stmt.setString(1, username);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated == 0) {
                throw new CourseNotFoundException("Student with username '" + username + "' not found.");
            } else {
                System.out.println("Student approved successfully.");
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error approving student", ex);
        }
    }

    @Override
    public List<Professor> getAllProfessors() {
        List<Professor> professors = new ArrayList<>();
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQLConstant.FETCH_PROFESSOR);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Professor professor = new Professor(
                    rs.getInt("professor_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("gender"),
                    rs.getInt("age"),
                    rs.getString("address"),
                    rs.getString("phone_number"),
                    rs.getString("email_id")
                );
                professors.add(professor);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error fetching professors", ex);
        }
        return professors;
    }

    @Override
    public boolean removeProfessor(int professorId) throws ProfessorRemovalException {
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQLConstant.REMOVE_PROFESSOR_FROM_PROFESSOR)) {
            stmt.setInt(1, professorId);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted == 0) {
                throw new ProfessorRemovalException("Professor with ID " + professorId + " not found.");
            } else {
                System.out.println("Professor removed successfully.");
                return true;
            }
        } catch (SQLException ex) {
            throw new ProfessorRemovalException("Error removing professor", ex);
        }
    }

    @Override
    public boolean setBillAmount(int courseId, int newBillAmount) {
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQLConstant.UPDATE_BILL)) {
            stmt.setInt(1, newBillAmount);
            stmt.setInt(2, courseId);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated == 0) {
                throw new BillAmountUpdateException("Course with ID " + courseId + " not found.");
            } else {
                System.out.println("Bill amount updated successfully for course.");
                return true;
            }
        } catch (SQLException ex) {
            throw new BillAmountUpdateException("Error updating bill amount", ex);
        }
    }

    @Override
    public boolean removeProfessorFromCourse(int courseId) {
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQLConstant.REMOVE_PROFESSOR_FROM_COURSE)) {
            stmt.setInt(1, courseId);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated == 0) {
                throw new RemoveProfessorFromCourseException("Professor not assigned to course with ID " + courseId);
            } else {
                System.out.println("Professor removed from course successfully.");
                return true;
            }
        } catch (SQLException ex) {
            throw new RemoveProfessorFromCourseException("Error removing professor from course", ex);
        }
    }

    private boolean professorExists(Connection conn, int professorId) throws SQLException {
        try (PreparedStatement pstmt = conn.prepareStatement(SQLConstant.PROFESSOR_EXIST)) {
            pstmt.setInt(1, professorId);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        }
    }

    private boolean courseExists(Connection conn, int courseId) throws SQLException {
        try (PreparedStatement pstmt = conn.prepareStatement(SQLConstant.COURSE_EXIST)) {
            pstmt.setInt(1, courseId);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        }
    }
}
