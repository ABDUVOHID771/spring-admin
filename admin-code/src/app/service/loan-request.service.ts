import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { LoanRequest } from '../domain/loan-request';
import { SendMessage } from '../domain/send-message';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class LoanRequestService {

  sendingMessage: SendMessage = new SendMessage();

  constructor(private httpClient: HttpClient) { }

  getLoanRequestList(id: any) {
    return this.httpClient.get<LoanRequest[]>(`http://localhost:8080/rest/loan-request/branch/${id}`);
  }

  getLoanRequestListAll() {
    return this.httpClient.get<LoanRequest[]>(`http://localhost:8080/rest/loan-request`);
  }

  getLoanRequest(id) {
    return this.httpClient.get<LoanRequest>(`http://localhost:8080/rest/loan-request/${id}`);
  }

  updateLoanRequest(id, loanRequest) {
    return this.httpClient.put<LoanRequest>(`http://localhost:8080/rest/loan-request/${id}`, loanRequest);
  }


  sendMessage(comingPhone, comingText) {
    const body = { phone: comingPhone, text: comingText }
    this.httpClient.post<any>('http://localhost:8080/sms/send', body).subscribe(
      data => {
        this.sendingMessage.messageId = data.messageID;
        this.sendingMessage.text = comingText;
        this.sendingMessage.result = data.result;
        this.sendingMessage.phone = comingPhone;
        this.saveSendMessage(this.sendingMessage);
        return data;
      });
  }

  saveSendMessage(sendingMessages) {
    this.httpClient.post<any>('http://localhost:8080/rest/send-message', sendingMessages).subscribe(
      data => console.log("COMING DATA : " + data)
    )
  }

  getImage(imageUrl: string): Observable<Blob> {
    return this.httpClient.post('http://localhost:8080/img/get',imageUrl, { responseType: 'blob' });
  }

}
