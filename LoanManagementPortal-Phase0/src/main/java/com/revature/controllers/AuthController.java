package com.revature.controllers;

import com.revature.models.User;
import com.revature.services.AuthService;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import jakarta.servlet.http.HttpSession;

public class AuthController {

    //Instantiate an AuthService to get access to the login method
    private AuthService authService = new AuthService();

    //LOGIN HANDLER - check out this syntax if you like lambdas
    public Handler loginHandler = ctx -> {

        //Extract username and password from the request body
        User loginUser = ctx.bodyAsClass(User.class);

        //Send the User object to the AuthService for authentication
        User loggedInUser = authService.login(loginUser);

        if(loggedInUser != null) {

            System.out.println(loggedInUser);

            //If the login is successful, create a session and store user info
            HttpSession ses = ctx.req().getSession(true); //true means create a new session if one doesn't exist

            //Save any desired user attributes in the session!
            ses.setAttribute("username", loggedInUser.getUsername());
            ses.setAttribute("userId", loggedInUser.getUserId());
            ses.setAttribute("userRole", loggedInUser.getRole());

            //Look how easy it is to set the session attribute!
            System.out.println("User logged in: " + ses.getAttribute("username"));

            /*Sessions are one of the classic ways to store user data
            Now, we can personalize the entire app for the logged-in user
            This makes it SO EASY to do stuff like:
                -Show a User only their own loans
                -Show a User only their user profile
                -Determine a User's Role if they're doing something that requires certain permissions*/


            //Set the response status to 200 OK and return the logged-in user
            ctx.status(200).json(loggedInUser);
        } else {
            //If login fails, throw an IllegalArgumentException
            throw new IllegalArgumentException("Invalid username or password.");
        }

    }; //needs a semicolon ;)

}
