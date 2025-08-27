import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Loan } from '../interfaces/loan';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoanService {
  
    constructor(private http:HttpClient){}

  //this will get filled with the relevant loan data (not very safe)
  public userLoans:Loan[] = [] //this will get populated after the GET

  getUserLoans(): Observable<any> {
 
    return this.http.get("http://localhost:8080/loans/pending", {withCredentials:true})

  }
}
