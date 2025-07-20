package com.revature.controllers;

import com.revature.models.Loan;
import com.revature.services.LoanService;
import io.javalin.http.Context;

//The Controller layer is responsible for handling HTTP requests and responses.
public class LoanController {

    //Instantiate a LoanService to invoke its methods
    private LoanService loanService = new LoanService();

    // Handler to apply for a loan
    public void applyForLoanHandler(Context ctx) {

        //POST-AUTH Refactor: Check if the user is logged in
        if(ctx.sessionAttribute("userId") == null) {
            // If not logged in, return a 401 Unauthorized
            ctx.status(401).result("Unauthorized: Please log in to view pending loans.");
            return;
        }

        // Extract loan data from the request body using ctx.bodyAsClass()
        Loan newLoan = ctx.bodyAsClass(Loan.class);

        // Call the service method to apply for a loan
        Loan createdLoan = loanService.applyForLoan(newLoan);
        // If successful, return a 201 (Created) and the created Loan object
        ctx.status(201).json(createdLoan);

        //If an Exception is thrown, it will be caught by the exception handler in main

    }

    // Handler to view all pending loans
    public void viewAllPendingLoansHandler(Context ctx) {

        //POST-AUTH Refactor: Check if the user is logged in
        if(ctx.sessionAttribute("userId") == null) {
            // If not logged in, return a 401 Unauthorized
            ctx.status(401).result("Unauthorized: Please log in to view pending loans.");
            return;
        }

        //POST-AUTH Refactor: Grab user ID out of the session to send to the service
        int userId = ctx.sessionAttribute("userId");

        // Call the service method to get all pending loans
        // Turn it into a JSON response and send it back to the client (200 OK by default)
        ctx.json(loanService.viewAllPendingLoans(userId));

    }

}
