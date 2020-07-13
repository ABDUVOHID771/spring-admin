import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { LoanType } from '../domain/loan-type';

@Injectable({
  providedIn: 'root'
})
export class LoanTypeService {

  constructor(private httpClient:HttpClient) { }

  getLoanTypeList(){
    return this.httpClient.get<LoanType[]>('http://localhost:8080/rest/loan-type');
  }

  getLoanType(id){
    return this.httpClient.get<LoanType>(`http://localhost:8080/rest/loan-type/${id}`);
  }

  updateLoanType(id,loanType){
    return this.httpClient.put<LoanType>(`http://localhost:8080/rest/loan-type/${id}`,loanType)
  }

}
