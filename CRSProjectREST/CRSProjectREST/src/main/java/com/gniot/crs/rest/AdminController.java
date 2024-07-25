package com.gniot.crs.rest;

import com.gniot.crs.bean.Course;
import com.gniot.crs.business.AdminInterface;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * AdminController handles admin-related API endpoints.
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminInterface adminService;

    /**
     * Constructor injection for AdminInterface.
     *
     * @param adminService The service implementation for admin operations.
     */
    public AdminController(AdminInterface adminService) {
        this.adminService = adminService;
    }

    /**
     * Adds a new course to the catalog.
     *
     * @param courseName The name of the course.
     * @param courseCode The code of the course.
     * @return A confirmation message.
     */
    @PostMapping(value = "/addCourse", produces = MediaType.APPLICATION_JSON_VALUE)
    public String addCourseToCatalog(@RequestParam String courseName, @RequestParam String courseCode) {
        adminService.addCourseToCatalog(courseName, courseCode);
        return "Course added to catalog";
    }

    /**
     * Removes a course from the catalog.
     *
     * @param courseId The ID of the course to be removed.
     * @return A confirmation message.
     */
    @DeleteMapping(value = "/removeCourse", produces = MediaType.APPLICATION_JSON_VALUE)
    public String removeCourseFromCatalog(@RequestParam int courseId) {
        adminService.removeCourseFromCatalog(courseId);
        return "Course removed from catalog";
    }

    /**
     * Updates the billing amount for a course.
     *
     * @param courseId      The ID of the course.
     * @param newBillAmount The new billing amount.
     * @return A confirmation message.
     */
    @PutMapping(value = "/setBillAmount", produces = MediaType.APPLICATION_JSON_VALUE)
    public String setBillAmount(@RequestParam int courseId, @RequestParam int newBillAmount) {
        adminService.setBillAmount(courseId, newBillAmount);
        return "Bill amount updated";
    }

    /**
     * Assigns a course to a professor.
     *
     * @param professorId The ID of the professor.
     * @param courseId    The ID of the course.
     * @return A confirmation message.
     */
    @PostMapping(value = "/assignCourse", produces = MediaType.APPLICATION_JSON_VALUE)
    public String assignCourseToProfessor(@RequestParam int professorId, @RequestParam int courseId) {
        adminService.assignCourseToProfessor(professorId, courseId);
        return "Course assigned to professor";
    }

    /**
     * Removes a professor from a course.
     *
     * @param courseId The ID of the course.
     * @return A confirmation message.
     */
    @DeleteMapping(value = "/removeProfessorFromCourse", produces = MediaType.APPLICATION_JSON_VALUE)
    public String removeProfessorFromCourse(@RequestParam int courseId) {
        adminService.removeProfessorFromCourse(courseId);
        return "Professor removed from course";
    }

    /**
     * Approves a professor.
     *
     * @param professorId The ID of the professor.
     * @return A confirmation message.
     */
    @PutMapping(value = "/approveProfessor", produces = MediaType.APPLICATION_JSON_VALUE)
    public String approveProfessor(@RequestParam int professorId) {
        adminService.approveProfessor(professorId);
        return "Professor approved";
    }

    /**
     * Removes a professor from the system.
     *
     * @param professorId The ID of the professor.
     * @return A confirmation message.
     */
    @DeleteMapping(value = "/removeProfessor", produces = MediaType.APPLICATION_JSON_VALUE)
    public String removeProfessor(@RequestParam int professorId) {
        adminService.removeProfessor(professorId);
        return "Professor removed";
    }

    /**
     * Approves a student.
     *
     * @param username The username of the student.
     * @return A confirmation message.
     */
    @PutMapping(value = "/approveStudent", produces = MediaType.APPLICATION_JSON_VALUE)
    public String approveStudent(@RequestParam String username) {
        adminService.approveStudent(username);
        return "Student approved";
    }

    /**
     * Retrieves the course catalog.
     *
     * @return A list of courses.
     */
    @GetMapping(value = "/courseCatalog", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Course> displayCourseCatalog() {
        return adminService.displayCourseCatalog();
    }

    /**
     * Lists students pending approval.
     *
     * @return A list of usernames of pending students.
     */
    @GetMapping(value = "/pendingStudents", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> listPendingStudents() {
        return adminService.listPendingStudents();
    }

    /**
     * Lists professors pending approval.
     *
     * @return A list of usernames of pending professors.
     */
    @GetMapping(value = "/pendingProfessors", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> listPendingProfessors() {
        return adminService.listPendingProfessor();
    }
}
