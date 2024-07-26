/**
 * 
 */
package com.gniot.crs.business;

import java.util.List;

import com.gniot.crs.bean.Course;
import com.gniot.crs.bean.Grade;
import com.gniot.crs.bean.Payment;
import com.gniot.crs.bean.Student;

/**
 * 
 */
public interface StudentInterface {
	public List<Course> browseCatalogForCoures();

//	public void addCourse();
	public void addCourse(int studentId, int courseId);
//	void removeCourse(int studentId,String courseIdStr);

	public List<Grade> viewGrades(int studentId, int courseId);



	public Student accountInfo(int studentId);

	public void payment();

	void printHorizontalLine(int... widths);

	public void removeCourse(int studentId, String courseId);

	public List<Payment> displayPaymentHistory(int studentId);



	

}
