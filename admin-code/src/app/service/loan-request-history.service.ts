import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { LoanRequestHistory } from '../domain/loan-request-history';

@Injectable({
  providedIn: 'root'
})
export class LoanRequestHistoryService {

  constructor(private httpClient:HttpClient) { }

  addHistory(loanHistory){
    return this.httpClient.post<any>('http://localhost:8080/rest/loan-request-history',loanHistory)
    .subscribe(data=>console.log("DATA IS SAVED " +data ));
  }

  getLoanRequestHistoryListAll() {
    return this.httpClient.get<LoanRequestHistory[]>(`http://localhost:8080/rest/loan-request-history`);
  }

}
