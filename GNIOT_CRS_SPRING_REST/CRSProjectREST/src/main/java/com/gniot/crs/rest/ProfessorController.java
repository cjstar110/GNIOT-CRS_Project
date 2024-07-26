package com.gniot.crs.rest;

import com.gniot.crs.bean.Course;
import com.gniot.crs.bean.GradeRequest;
import com.gniot.crs.bean.Student;
import com.gniot.crs.business.ProfessorInterface;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ProfessorController handles professor-related API endpoints.
 */
@RestController
@RequestMapping("/professor")
public class ProfessorController {

    private final ProfessorInterface professorService;

    /**
     * Constructor injection for ProfessorInterface.
     *
     * @param professorService The service implementation for professor operations.
     */
    public ProfessorController(ProfessorInterface professorService) {
        this.professorService = professorService;
    }

    /**
     * Adds a grade for a student.
     *
     * @param gradeRequest The request body containing grade details.
     * @return A confirmation message.
     */
    @PostMapping(value = "/addGrade", produces = MediaType.APPLICATION_JSON_VALUE)
    public String addGrade(@RequestBody GradeRequest gradeRequest) {
        professorService.addGrade(gradeRequest.getStudentId(), gradeRequest.getCourseId(), gradeRequest.getGrade(), gradeRequest.getProfessorId(), gradeRequest.getGradeId());
        return "Grade added successfully";
    }

    /**
     * Updates an existing grade for a student.
     *
     * @param gradeRequest The request body containing updated grade details.
     * @return A confirmation message.
     */
    @PatchMapping(value = "/updateGrade", produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateGrade(@RequestBody GradeRequest gradeRequest) {
        professorService.addGrade(gradeRequest.getStudentId(), gradeRequest.getCourseId(), gradeRequest.getGrade(), gradeRequest.getProfessorId(), gradeRequest.getGradeId());
        return "Grade updated successfully";
    }

    /**
     * Retrieves the list of students enrolled in courses taught by the professor.
     *
     * @param professorId The ID of the professor.
     * @return A list of enrolled students.
     */
    @GetMapping(value = "/viewEnrolledStudents", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Student> viewEnrolledStudents(@RequestParam int professorId) {
        return professorService.viewEnrolledStudents(professorId);
    }

    /**
     * Retrieves the list of courses taught by the professor.
     *
     * @param professorId The ID of the professor.
     * @return A list of courses.
     */
    @GetMapping(value = "/courses", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Course> getProfessorCourses(@RequestParam int professorId) {
        return professorService.getProfessorCourses(professorId);
    }
}
