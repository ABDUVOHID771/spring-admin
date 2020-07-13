import { Component, OnInit } from '@angular/core';
import { LoanType } from 'src/app/domain/loan-type';
import { ActivatedRoute, Router } from '@angular/router';
import { LoanTypeService } from 'src/app/service/loan-type.service';
import { Status } from 'src/app/domain/status.enum';
import { LoginService } from 'src/app/service/login.service';

@Component({
  selector: 'app-edit-loan-type',
  templateUrl: './edit-loan-type.component.html',
  styleUrls: ['./edit-loan-type.component.css']
})
export class EditLoanTypeComponent implements OnInit {

  id: number;
  loanType: LoanType;
  status:Status;
  username:string;
  isRoleSuperAdmin: boolean = false;

  constructor(private loginService:LoginService,private activatedRoute: ActivatedRoute, private loanTypeService: LoanTypeService, private router: Router) { }

  ngOnInit(): void {

    this.isSuperAdmin(this.loginService.getUserRole());
    this.id = this.activatedRoute.snapshot.params['id'];

    this.loanType = new LoanType();

    this.loanTypeService.getLoanType(this.id).subscribe(data => this.loanType = data);

  }

  edit(id) {
    this.loanTypeService.updateLoanType(this.id,this.loanType).subscribe(data=>this.router.navigate(['/admin/loan-type']));
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
