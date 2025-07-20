package com.revature.DAOs;

import com.revature.models.Loan;

import java.util.List;

//Again, this Interface helps organize the methods we want to implement for our Loan DAO
//We'll implement this Interface in LoanDAO, and write the actual code for the methods there

public interface LoanDAOInterface {

    Loan applyForLoan(Loan loan);

    List<Loan> viewAllPendingLoans(int userId);

    Loan editLoan(Loan loan);

    String closeLoan(int loanId);

}
