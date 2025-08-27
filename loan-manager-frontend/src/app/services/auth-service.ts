import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../interfaces/user';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

   constructor(private http:HttpClient){}

  //this will get filled with the relevant logged-in user data (not very safe)
  public loggedInUser:User = {} //this will get populated after login

  login(username:string, password:string): Observable<any> {

    console.log(username + " " + password)
 
    return this.http.post("http://localhost:8080/auth", {username, password}, {withCredentials:true})

  }
  
}
