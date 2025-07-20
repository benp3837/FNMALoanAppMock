package com.revature.models;

import java.util.Arrays;

public class Loan {

    private int loanId;
    private int loanAmount;
    private String loanStatus;
    private int[] userIds; //This is for the users associated with the loan

    //boilerplate

    public Loan() {
        super();
    }

    public Loan(int loanId, int loanAmount, String loanStatus, int[] userIds) {
        super();
        this.loanId = loanId;
        this.loanAmount = loanAmount;
        this.loanStatus = loanStatus;
        this.userIds = userIds;
    }

    //Constructor for new loan - no loanId, and loanStatus set to "PENDING"
    public Loan(int loanAmount, int[] userIds) {
        super();
        this.loanAmount = loanAmount;
        this.loanStatus = "PENDING";
        this.userIds = userIds;
    }

    public int getLoanId() {
        return loanId;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    public int getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(int loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }

    public int[] getUserIds() {
        return userIds;
    }

    public void setUserIds(int[] userIds) {
        this.userIds = userIds;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "loanId=" + loanId +
                ", loanAmount=" + loanAmount +
                ", loanStatus='" + loanStatus + '\'' +
                ", userIds=" + Arrays.toString(userIds) +
                '}';
    }
}
