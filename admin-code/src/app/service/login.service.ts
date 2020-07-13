import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class LoginService {

  branchId: number;

  constructor(private httpClient: HttpClient) { }


  isUserLoggedIn() {
    let user = sessionStorage.getItem('authenticatedUser');
    return !(user === null);
  }

  logout() {
    sessionStorage.removeItem('token');
    sessionStorage.removeItem('role');
  }

  executeAuthenticationService(comingLogin, comingPassword) {

    let body = { username: comingLogin, password: comingPassword };
    let username = comingLogin;
    console.log("LOGIN : " + comingLogin + " : " + "PASS : " + comingPassword)
    console.log(body);
    return this.httpClient.post<any>('http://localhost:8080/authenticate',body).
      pipe(map(
        data => {
          console.log("Bearer " + data.jwt)
          sessionStorage.setItem('authenticatedUser', username);
          sessionStorage.setItem('Authorization', "Bearer " + data.jwt);
          sessionStorage.setItem('brachId', data.branchId);
          sessionStorage.setItem('role', data.role);
          sessionStorage.setItem('userId', data.userId);
          return data;
        }
      ));
  }

  getBranchId() {
    return sessionStorage.getItem('brachId');
  }

  getAuthenticatedToken() {
    return sessionStorage.getItem('Authorization');
  }
  getUserRole() {
    return sessionStorage.getItem('role');
  }

  getUserId() {
    return sessionStorage.getItem('userId');
  }

  getUsername() {
    return sessionStorage.getItem('authenticatedUser');
  }

}
