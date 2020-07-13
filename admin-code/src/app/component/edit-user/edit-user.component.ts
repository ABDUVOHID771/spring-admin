import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/domain/user';
import { LoginService } from 'src/app/service/login.service';
import { ActivatedRoute, Router } from '@angular/router';
import { LoanTypeService } from 'src/app/service/loan-type.service';
import { LoanType } from 'src/app/domain/loan-type';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit {

  id: number;
  user : User=new User();
  username:string;
  superAdmin:boolean=false;
  isRoleSuperAdmin: boolean = false;

  constructor(private loginService:LoginService,private activatedRoute: ActivatedRoute, private userService: UserService, private router: Router) { }

  ngOnInit(): void {

    this.id = this.activatedRoute.snapshot.params['id'];

    this.user = new User();
    this.isSuperAdmin(this.loginService.getUserRole());
    this.userService.getUser(this.id).subscribe(data => {
      this.user = data;
      if(this.user.userRole==="SUPERADMIN"){this.superAdmin=true;}});

  }

  edit(id) {
    this.userService.updateUser(this.id,this.user).subscribe(data=>this.router.navigate(['/admin/user']));
  }

  logout(){
    this.loginService.logout();
    this.router.navigate(['login']);
  }

  isSuperAdmin(role) {
    if (role == "SUPERADMIN") {
      this.isRoleSuperAdmin = true;
    }
  }

}
