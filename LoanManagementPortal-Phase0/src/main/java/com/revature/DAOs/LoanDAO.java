package com.revature.DAOs;

import com.revature.models.Loan;
import com.revature.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class LoanDAO implements LoanDAOInterface {

    //Inserting a Loan is slightly trickier since we need to add a record to the LoanUsers table too
    @Override
    public Loan applyForLoan(Loan loan) {

        try(Connection conn = ConnectionUtil.getConnection()) {

            //Let's start by creating the Loan, so the loans table can point to it
            String sql = "INSERT INTO loans (loan_amount, loan_status) VALUES (?, 'PENDING')";

            //This time we need to make sure to return the primary key (loan_id) of the new Loan
            PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, loan.getLoanAmount());

            //Execute the insert, and save the new loan id using getGeneratedKeys()
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            int loanId = 0;

            if(rs.next()) {
                loanId = rs.getInt(1);
            }

            //Now, with the new loan id, insert a record into the LoanUsers table for every user ID
            for(int id:loan.getUserIds()){
                String loanUserSql = "INSERT INTO loan_users (loan_id, user_id) VALUES (?, ?)";

                PreparedStatement loanUserPs = conn.prepareStatement(loanUserSql);
                loanUserPs.setInt(1, loanId);
                loanUserPs.setInt(2, id);

                //Execute the insert into LoanUsers
                loanUserPs.executeUpdate();
            }

            //Return the new Loan!
            loan.setLoanId(loanId);
            loan.setLoanStatus("PENDING");
            System.out.println("Loan application with ID " + loanId + " created");
            return loan;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    //TODO: Add User to existing loan is part of a user story in iteration 3. They can do it early tho
    //It would just insert a new record into the LoanUsers table - new user id + existing loan id


    //This is centered around a simple "select *" query (until we write the join to get loans by user)
    //While simple, it's a little tedious to convert all the records into a List<> of Loan objects
    @Override
    public List<Loan> viewAllPendingLoans(int userId) {

        try(Connection conn = ConnectionUtil.getConnection()) {

            //Cant get loans by user id since userId is in the LoanUsers table. How do we handle that?
            //This long gangly join! (THIS CAN BE A PRACTICE QUESTION FOR THEM!!!!!!!)
            //*****START WITH JUST ALL PENDING LOANS, THEN THEY FIGURE OUT THE JOIN AFTER AUTH****
            //Make sure they understand the aliases and why we need them
            String sql = "SELECT l.*, lu.user_id " +
                    "FROM loans l " +
                    "JOIN loan_users lu ON l.loan_id = lu.loan_id " +
                    "WHERE l.loan_status = 'PENDING' AND lu.user_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            //We can use a List to store all the Loan objects we create
            List<Loan> loans = new java.util.ArrayList<>();

            while(rs.next()) {
                //Create a new Loan object for each record
                Loan loan = new Loan();
                loan.setLoanId(rs.getInt("loan_id"));
                loan.setLoanAmount(rs.getInt("loan_amount"));
                loan.setLoanStatus(rs.getString("loan_status"));

                //Add the Loan to our List
                loans.add(loan);
            }

            return loans; //return the List of Loans!

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

    //Good one to make them try on their own
    @Override
    public Loan editLoan(Loan loan) {
        return null;
    }

    //Good one to make them try on their own
    @Override
    public String closeLoan(int loanId) {
        return "";
    }
}
