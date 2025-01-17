package com.gniot.crs.dao;

import com.gniot.crs.bean.Professor;
import com.gniot.crs.bean.Student;
import com.gniot.crs.bean.User;
import com.gniot.crs.exception.RegistrationException;

public interface UserDAOInterface {
    String login(String username, String password) throws Exception;
    void changePassword(String username, String oldPassword, String newPassword) throws Exception;
    User findUserByUsername(String username);
    boolean updateDetails(int userId, String username, String department);
    boolean updatePassword(String password);
	/**
	 * Registers a new user. This method allows a new user to register by providing
	 * a username, password, and role. If the user is a student, additional
	 * information such as name, gender, age, etc., is collected. After successful
	 * registration, the user's information is stored in the database. Students are
	 * required to wait for admin approval before they can log in.
	 * @param professor 
	 * @param student 
	 */
	void register(User user, Student student, Professor professor) throws RegistrationException;
	/**
	 * Registers a new user. This method allows a new user to register by providing
	 * a username, password, and role. If the user is a student, additional
	 * information such as name, gender, age, etc., is collected. After successful
	 * registration, the user's information is stored in the database. Students are
	 * required to wait for admin approval before they can log in.
	 */
	/**
	 * Registers a new user. This method allows a new user to register by providing
	 * a username, password, and role. If the user is a student, additional
	 * information such as name, gender, age, etc., is collected. After successful
	 * registration, the user's information is stored in the database. Students are
	 * required to wait for admin approval before they can log in.
	 */

	
}
