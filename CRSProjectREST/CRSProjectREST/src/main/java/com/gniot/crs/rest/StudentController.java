/**
 * Controller for handling student-related API endpoints.
 */
package com.gniot.crs.rest;

import java.util.List;

import com.gniot.crs.bean.Course;
import com.gniot.crs.bean.Grade;
import com.gniot.crs.bean.Payment;
import com.gniot.crs.bean.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.gniot.crs.business.*;

/**
 * StudentController handles API requests related to student operations.
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private final StudentInterface studentService;

    /**
     * Constructor for injecting StudentInterface.
     *
     * @param studentService The service implementation for student operations.
     */
    public StudentController(StudentInterface studentService) {
        this.studentService = studentService;
    }

    /**
     * Retrieves the course catalog.
     *
     * @return A list of courses.
     */
    @GetMapping(value = "/courses", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Course> browseCatalogForCoures() {
        return studentService.browseCatalogForCoures();
    }

    /**
     * Adds a course for a student.
     *
     * @param studentId The ID of the student.
     * @param courseId  The ID of the course.
     * @return A confirmation message.
     */
    @PostMapping(value = "/addCourse", produces = MediaType.APPLICATION_JSON_VALUE)
    public String addCourse(@RequestParam int studentId, @RequestParam int courseId) {
        studentService.addCourse(studentId, courseId);
        return "Course added successfully";
    }

    /**
     * Removes a course for a student.
     *
     * @param studentId The ID of the student.
     * @param courseId  The ID of the course.
     * @return A confirmation message.
     */
    @DeleteMapping(value = "/removeCourse", produces = MediaType.APPLICATION_JSON_VALUE)
    public String removeCourse(@RequestParam int studentId, @RequestParam String courseId) {
        studentService.removeCourse(studentId, courseId);
        return "Course removed successfully";
    }

    /**
     * Retrieves the grades for a student in a specific course.
     *
     * @param studentId The ID of the student.
     * @param courseId  The ID of the course.
     * @return A list of grades.
     */
    @GetMapping(value = "/grades", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Grade> viewGrades(@RequestParam int studentId, @RequestParam int courseId) {
        return studentService.viewGrades(studentId, courseId);
    }

    /**
     * Retrieves account information for a student.
     *
     * @param studentId The ID of the student.
     * @return The student's account information.
     */
    @GetMapping(value = "/accountInfo", produces = MediaType.APPLICATION_JSON_VALUE)
    public Student accountInfo(@RequestParam int studentId) {
        return studentService.accountInfo(studentId);
    }

    /**
     * Displays the payment history for a student.
     *
     * @param studentId The ID of the student.
     * @return A list of payment records.
     */
    @GetMapping(value = "/payment", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Payment> displayPaymentHistory(@RequestParam int studentId) {
        return studentService.displayPaymentHistory(studentId);
    }
}
