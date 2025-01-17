package com.gniot.crs.bean;

import java.util.List;

public class User {
    public User() {}

    private String username;
    private String password;
    private String role;
    private boolean approved;  // New field to track approval status
    
    private Student student;
    
    

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.approved = !role.equalsIgnoreCase("student"); // Students start as not approved
    }

    public User(String username, String password, String role, Student student) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.student = student;
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
    	return role != null ? role : "";
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<String> getEnrolledCourses() {
        // TODO Auto-generated method stub
        return null;
    }

    public String getId() {
        // TODO Auto-generated method stub
        return null;
    }

	public void setId(String string) {
		// TODO Auto-generated method stub
		
	}

	public void setUsername(String username) {
		this.username = username;
		// TODO Auto-generated method stub
		
	}

	public void setRole(String role) {
		// TODO Auto-generated method stub
		this.role = role;
		
	}
}
