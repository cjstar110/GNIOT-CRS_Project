package com.gniot.crs.bean;

import java.util.List;


public class User {
	
	    private String username;
	    private String password;
	    private String role;
	    

	    public User(String username, String password, String role) {
	        this.username = username;
	        this.password = password;
	        this.role = role;
	    }
	    private boolean approved;  // New field to track approval status
	    private Student Student;
	    
	    public User(String username, String password, String role, Student Student) {
	        this.username = username;
	        this.password = password;
	        this.role = role;
	        this.setStudent(Student);

	        // Set approved based on role:
	        this.approved = !role.equalsIgnoreCase("student"); // Students start as not approved
	    }
	
	    public boolean isApproved() {
	        return approved;
	    }

	    public void setApproved(boolean approved) {
	        this.approved = approved;
	    }

	    public String getUsername() {
	        return username;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public String getRole() {
	        return role;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

		public Student getStudent() {
			return Student;
		}

		public void setStudent(Student Student) {
			this.Student = Student;
		}
		public List<String> getEnrolledCourses() {
			// TODO Auto-generated method stub
			return null;
		}

		public String getId() {
			// TODO Auto-generated method stub
			return null;
		}
	}

