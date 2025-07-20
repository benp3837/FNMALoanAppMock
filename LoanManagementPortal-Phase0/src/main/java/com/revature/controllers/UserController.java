package com.revature.controllers;

import com.revature.models.User;
import com.revature.services.UserService;
import io.javalin.http.Context;

//The Controller layer is responsible for taking in HTTP requests and sending back HTTP responses.
public class UserController {

    //TODO: Haven't added any auth stuff to these functionalities 

    //Instantiate a UserService to invoke its methods
    private UserService userService = new UserService();

    //Handler to create a new user - using the ctx object from javalin
    public void createUserHandler(Context ctx) {

        //Extract user data from the request body using ctx.bodyAsClass()
        User newUser = ctx.bodyAsClass(User.class);

        //Call the service method to insert the user... 1 of 2 things can happen:
        try {
            //If the Service returns a User, the insert was successful
            //return a 201 (Created) and the created User object
            User createdUser = userService.insertUser(newUser);
            ctx.status(201).json(createdUser);
        } catch(IllegalArgumentException e) {
            //If the Service throws an Exception, return a 400 Bad Request with the error message
            ctx.status(400).result(e.getMessage());
        }

    }

    //Handler to edit an existing user
    public void editUserHandler(Context ctx) {

        //Extract user data from the request body
        User updatedUser = ctx.bodyAsClass(User.class);

        //Call the service method to edit the user
        try {
            User editedUser = userService.editUser(updatedUser);
            ctx.status(200).json(editedUser);
        } catch(IllegalArgumentException e) {
            //If the Service throws an Exception, return a 400 Bad Request with the error message
            ctx.status(400).result(e.getMessage());
        }

    }

}
