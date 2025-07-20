package com.revature.services;

import com.revature.DAOs.LoanDAO;
import com.revature.models.Loan;

import java.util.List;

public class LoanService {

    LoanDAO loanDAO = new LoanDAO();

    public Loan applyForLoan(Loan loan) {

        //Validate loan details if necessary
        if (loan.getLoanAmount() <= 0) {
            throw new IllegalArgumentException("Loan amount must be greater than zero.");
        }

        //If user ID is valid, proceed with the loan application
        if (loan.getUserIds().length <= 0) {
            throw new IllegalArgumentException("Must add at least one user to loan");
        } else {
            // Call the DAO method to apply for a loan
            return loanDAO.applyForLoan(loan);
        }

    }

    //basic Get all methods tend to be pretty simple in the Service. No input to validate
    public List<Loan> viewAllPendingLoans(int userId){
        // Call the DAO method to get all loans
        return loanDAO.viewAllPendingLoans(userId);
    }

}
