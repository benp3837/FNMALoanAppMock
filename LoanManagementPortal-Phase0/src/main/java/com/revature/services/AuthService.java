package com.revature.services;

import com.revature.DAOs.AuthDAO;
import com.revature.models.User;

public class AuthService {

    //Instantate the authDAO to get access to login()
    private AuthDAO authDAO = new AuthDAO();

    //Validate the user credentials and hit the DAO to authenticate the user
    public User login(User user) {
        // Validate the input
        if (user.getUsername() == null || user.getUsername().isBlank() || user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Username and password must not be empty.");
        }

        // Call the DAO method to authenticate the user
        return authDAO.login(user);
    }

}
