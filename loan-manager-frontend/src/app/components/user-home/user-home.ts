import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth-service';
import { LoanService } from '../../services/loan-service';
import { Loan } from '../../interfaces/loan';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';


@Component({
  selector: 'app-user-home',
  imports: [CommonModule, FormsModule],
  templateUrl: './user-home.html',
  styleUrl: './user-home.css'
})
//Gotta import and implement OnInit to use ngOnInit!!
export class UserHome implements OnInit {

  loans:Loan[] = []

  constructor(public authService:AuthService, public loanService:LoanService){}

  //ngOnInit is a great way to define some functionality to happen when the component initializes 
  //(happens during render)
  ngOnInit(): void {
    console.log("hi")
    this.getLoans()
  }

  getLoans(){
    this.loanService.getUserLoans().subscribe({
        next: (response) => {

          console.log(response)

          //set the info in the service (raw, global data. not super safe, just for now)
          this.loanService.userLoans = response
          
          this.loans = response

          console.log(this.loans)
 
        },
        error: (error) => {
          console.log(error)
          alert(error.error)
        }
      })
  }

  //edit loan modal--------------------

  showModal = false;
selectedLoan: any = {};

openEditModal(loan: any) {
  this.selectedLoan = { ...loan };
  this.showModal = true;
}

closeEditModal() {
  this.showModal = false;
}

saveLoan() {
  // Call your service to save changes
  this.closeEditModal();
}

}
