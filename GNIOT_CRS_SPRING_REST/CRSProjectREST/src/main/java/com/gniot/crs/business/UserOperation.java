package com.gniot.crs.business;

import com.gniot.crs.bean.Professor;
import com.gniot.crs.bean.Student;
import com.gniot.crs.bean.User;
import com.gniot.crs.dao.UserDAOInterface;
import com.gniot.crs.exception.RegistrationException;
import org.springframework.stereotype.Service;

@Service
public class UserOperation implements UserInterface {
    private final UserDAOInterface userDAO;

    public UserOperation(UserDAOInterface userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void register(User user, Student student, Professor professor) throws RegistrationException {
        userDAO.register(user,student,professor);
    }

    @Override
    public String login(String username, String password) throws Exception {
       return userDAO.login(username, password);
    }

    @Override
    public void changePassword(String username, String oldPassword, String newPassword) throws Exception {
        userDAO.changePassword(username, oldPassword, newPassword);
    }

    @Override
    public User findUserByUsername(String username) {
        return userDAO.findUserByUsername(username);
    }

    @Override
    public boolean updateDetails(int userId, String username, String department) {
        return userDAO.updateDetails(userId, username, department);
    }

    @Override
    public boolean updatePassword(String password) {
        return userDAO.updatePassword(password);
    }
}
