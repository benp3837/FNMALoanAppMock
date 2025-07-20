package com.revature.DAOs;

import com.revature.models.Loan;
import com.revature.models.User;

import java.util.List;

//The associates can implement all of this easily if they understand the Loan and User functionalities
public class ManagerDAO implements ManagerDAOInterface{


    @Override
    public User insertManager(User user) {
        return null;
    }

    @Override
    public List<Loan> viewAllLoans() {
        return List.of();
    }

    //Just change the status of the loan to "APPROVED" or "REJECTED"
    @Override
    public Loan approveOrRejectLoan(int loanId, boolean approve) {
        return null;
    }
}
