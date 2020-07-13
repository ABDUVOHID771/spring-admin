import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../domain/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpClient: HttpClient) { }

  getUserList() {
    return this.httpClient.get<User[]>('http://localhost:8080/rest/user-loan');
  }

  getUser(id){
    return this.httpClient.get<User>(`http://localhost:8080/rest/user-loan/${id}`)
  }

  updateUser(id,user){
    return this.httpClient.put<User>(`http://localhost:8080/rest/user-loan/${id}`,user);
  }

}
