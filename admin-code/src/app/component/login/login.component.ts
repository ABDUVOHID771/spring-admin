import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/service/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username:string;
  password:string;
  invalidLogin:boolean;

  constructor(private loginService:LoginService,private router:Router) { }

  ngOnInit(): void {
  }

  login(){
    this.loginService.executeAuthenticationService(this.username, this.password).
      subscribe(data => {
        this.router.navigate(['admin/loan-type']);
        this.invalidLogin = false;
      },
      error=>{
        console.log(error);
        this.invalidLogin = true;
      });
  }

}