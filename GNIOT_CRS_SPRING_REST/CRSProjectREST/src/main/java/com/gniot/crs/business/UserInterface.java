/**
 * 
 */
package com.gniot.crs.business;

import com.gniot.crs.bean.Professor;
import com.gniot.crs.bean.Student;
import com.gniot.crs.bean.User;
import com.gniot.crs.exception.RegistrationException;

/**
 * 
 */
public interface UserInterface {
	public boolean updateDetails(int userID, String userName, String userDepartment);
	public boolean updatePassword(String password);
//	void register(User user) throws RegistrationException;
	String login(String username, String password) throws Exception;
	void changePassword(String username, String oldPassword, String newPassword) throws Exception;
	User findUserByUsername(String username);
	public void register(User user, Student student, Professor professor)throws RegistrationException;

}
