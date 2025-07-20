package com.revature.services;

import com.revature.DAOs.UserDAO;
import com.revature.models.User;

//Service Classes contain BUSINESS LOGIC
//What's that? anything that doesn't have to do with HTTP (Controllers) or the Database (DAOs)
    //User input validation, DB error handling, data formatting, etc.
public class UserService {

    UserDAO userDAO = new UserDAO();

    //Insert a new user
    public User insertUser(User user) {

        //Checking if the username is already taken before trying to insert the user
        if(userDAO.getUserByUsername(user.getUsername()) != null){
            //If we get here, the username is not taken, so we can insert the user
            throw new IllegalArgumentException("Username already taken!");
        } else {
            return userDAO.insertUser(user);
        }

    }

    //Update an existing user
    public User editUser(User user) {

        //make sure the inputted values aren't empty
        if(user.getUsername() == null || user.getUsername().isBlank()){
            throw new IllegalArgumentException("Username cannot be empty!");
        }

        //TODO: would do the same check^ for email, role, etc.

        //perform the update if the fields are valid
        return userDAO.editUser(user);
    }

}
