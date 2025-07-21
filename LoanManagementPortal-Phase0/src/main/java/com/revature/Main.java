package com.revature;

import com.revature.DAOs.UserDAO;
import com.revature.controllers.AuthController;
import com.revature.controllers.LoanController;
import com.revature.controllers.UserController;
import com.revature.models.Loan;
import com.revature.models.User;
import com.revature.services.LoanService;
import com.revature.services.UserService;
import com.revature.utils.ConnectionUtil;
import com.revature.utils.ConnectionUtilSQLite;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello, World!");

        //Instantiate our Controller Classes
        UserController userController = new UserController();
        LoanController loanController = new LoanController();
        AuthController authController = new AuthController();

        //javalin object creation
        Javalin app = Javalin.create().start();

        //quick tester endpoint -
        //GET requests to the base url (localhost:8080) will greet the user
        app.get("/", ctx -> ctx.result("Hello, Javalin!"));

        //handlers for UserController
        app.post("/users", userController::createUserHandler); //Create a new user
        app.put("/users", userController::editUserHandler); //Edit an existing user

        //handlers for LoanController
        app.post("/loans", loanController::applyForLoanHandler); //Apply for a new loan
        app.get("/loans/pending", loanController::viewAllPendingLoansHandler); //View all pending loans

        //Login is a POST request, since we're sending a username and password
        //Note the slight difference in syntax since we used a Lambda in the Controller for this one
        app.post("/auth", authController.loginHandler);

        //Exception handler for IllegalArgumentException
        app.exception(IllegalArgumentException.class, (e, ctx) -> {
            // Return a 400 Bad Request with the error message
            ctx.status(400).result(e.getMessage());
        });

        //TODO: we'd have other handlers for other exceptions, like SQLException, etc.


        //OLD PRE-JAVALIN STUFF - Calling the Service layer directly from main

        try (Connection conn = ConnectionUtil.getConnection()) {
            System.out.println("Connected to DB successfully!");

            UserService userService = new UserService();
            LoanService loanService = new LoanService();

            //Insert a new user
            //userService.insertUser(new User("bigjavaguy", "iluvjava@oracle.com", "User"));

            //Update user 1's username
            //userService.editUser(new User(1, "smalljavaguy", "javayeah@gmail.com", "User"));

            //Users apply for a loan of $5000
            //loanService.applyForLoan(new Loan(5000), new int[] {1, 2, 3, 4});

            //View all loans (could have just used a print statement instead of forEach btw)
            //loanService.viewAllPendingLoans().forEach(System.out::println);

        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
