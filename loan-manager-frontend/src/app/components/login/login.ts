import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { CardModule } from 'primeng/card';
import { InputTextModule } from 'primeng/inputtext';
import "../../../styles.css"
import { AuthService } from '../../services/auth-service';
import { Router, RouterModule } from '@angular/router';


@Component({
  selector: 'app-login',
  imports: [FormsModule, CardModule, InputTextModule, ButtonModule],
  templateUrl: './login.html',
  styleUrl: './login.css'
})
export class Login {

  //Injecting the AuthService, needs to be private (encapsulated!)
  constructor(private authService:AuthService, private router:Router){}

    username = '';
    password = '';

    onLogin() {
      
      //Call to the service, which sends the login request
      //Remember username/password are captured automatically thanks to [(ngModel)]
      this.authService.login(this.username, this.password).subscribe({
        next: (response) => {

          console.log(response)

          console.log("Login success for " + response.username + " with role " + response.role)

          //set the user info in the service (raw, global data. not super safe, just for now)
          this.authService.loggedInUser = response
          
          //Switch the component to Manager Homepage if you're a manager
          if(response.role === "Manager"){
            this.router.navigateByUrl("/managerHome")
          } else {
            //Switch the component to User Home if not a Manager
            this.router.navigateByUrl("/userHome")
          }
        },
        error: (error) => {
          console.log(error)
          alert(error.error)
        }
      })
  }

}

