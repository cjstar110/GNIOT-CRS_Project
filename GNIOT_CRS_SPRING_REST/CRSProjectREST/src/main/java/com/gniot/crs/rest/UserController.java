package com.gniot.crs.rest;

import com.gniot.crs.bean.RegistrationRequest;
import com.gniot.crs.bean.User;
import com.gniot.crs.business.UserInterface;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * UserController handles user-related API endpoints.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserInterface userService;

    /**
     * Constructor for injecting UserInterface.
     *
     * @param userService The service implementation for user operations.
     */
    public UserController(UserInterface userService) {
        this.userService = userService;
    }

    /**
     * Registers a new user.
     *
     * @param registrationRequest The request body containing registration details.
     * @return A confirmation message.
     */
    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public String registerUser(@RequestBody RegistrationRequest registrationRequest) {
        try {
            userService.register(
                registrationRequest.getUser(),
                registrationRequest.getStudent(),
                registrationRequest.getProfessor()
            );
            return "User registered successfully";
        } catch (Exception e) {
            return "Error registering user: " + e.getMessage();
        }
    }

    /**
     * Logs in a user.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @return A login message.
     */
    @GetMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public String loginUser(@RequestParam String username, @RequestParam String password) {
        try {
            String loginMessage = userService.login(username, password);
            return loginMessage;
        } catch (Exception e) {
            return "Error logging in: " + e.getMessage();
        }
    }

    /**
     * Changes the password for a user.
     *
     * @param username    The username of the user.
     * @param oldPassword The old password of the user.
     * @param newPassword The new password of the user.
     * @return A confirmation message.
     */
    @PutMapping(value = "/changePassword", produces = MediaType.APPLICATION_JSON_VALUE)
    public String changeUserPassword(@RequestParam String username, @RequestParam String oldPassword, @RequestParam String newPassword) {
        try {
            userService.changePassword(username, oldPassword, newPassword);
            return "Password changed successfully";
        } catch (Exception e) {
            return "Error changing password: " + e.getMessage();
        }
    }

    /**
     * Finds a user by their username.
     *
     * @param username The username of the user.
     * @return The user details.
     */
    @GetMapping(value = "/find", produces = MediaType.APPLICATION_JSON_VALUE)
    public User findUserByUsername(@RequestParam String username) {
        return userService.findUserByUsername(username);
    }

    /**
     * Updates user details.
     *
     * @param userId     The ID of the user.
     * @param username   The new username of the user.
     * @param department The new department of the user.
     * @return A confirmation message.
     */
    @PutMapping(value = "/updateDetails", produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateUserDetails(@RequestParam int userId, @RequestParam String username, @RequestParam String department) {
        boolean success = userService.updateDetails(userId, username, department);
        return success ? "User details updated successfully" : "Error updating user details";
    }

    /**
     * Updates the password for a user.
     *
     * @param password The new password of the user.
     * @return A confirmation message.
     */
    @PutMapping(value = "/updatePassword", produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateUserPassword(@RequestParam String password) {
        boolean success = userService.updatePassword(password);
        return success ? "Password updated successfully" : "Error updating password";
    }
}
