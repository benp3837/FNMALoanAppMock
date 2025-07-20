package com.revature.DAOs;

import com.revature.models.Loan;
import com.revature.models.User;

import java.util.List;

/*Remember, all that separates Managers from Users is their role in the system
But I still like to separate the User/Manager interfaces for clarity between the two roles
This is a design choice I make for early-phase projects especially.

But realistically, I'd consider merging the DAOs or having a parent interface
I lean toward that in a less verbose library like Spring Data */
public interface ManagerDAOInterface {

    //Instead of a DAO method, we could make the createUser functionality check for manager role
    //If the user is trying to insert a manager, make sure THEY'RE logged in as a manager first.
    User insertManager(User user);

    //Every loan, not just the User's. Super easy exercise for associates if you want a 20 min break :)
    List<Loan> viewAllLoans();

    Loan approveOrRejectLoan(int loanId, boolean approve);

}
