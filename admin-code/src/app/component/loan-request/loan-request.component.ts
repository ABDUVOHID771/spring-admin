import { Component, OnInit } from '@angular/core';
import { LoanRequestService } from 'src/app/service/loan-request.service';
import { LoanRequest } from 'src/app/domain/loan-request';
import * as $ from 'jquery';
import 'datatables.net';
import 'datatables.net-bs4';
import { ChangeDetectorRef } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/service/login.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-loan-request',
  templateUrl: './loan-request.component.html',
  styleUrls: ['./loan-request.component.css']
})
export class LoanRequestComponent implements OnInit {

  loanRequests: LoanRequest[];
  dataTable: any;
  loanRequest: LoanRequest = new LoanRequest();
  username: string;
  branchId: number;
  isRoleSuperAdmin: boolean = false;


  constructor(private userService: UserService, private loginService: LoginService, private router: Router, private loanRequestService: LoanRequestService, private chRef: ChangeDetectorRef) { }

  ngOnInit(): void {
    this.refresh();


  }

  getLoanRequestById(id) {
    this.loanRequestService.getLoanRequest(id).subscribe(data => { this.loanRequest = data; console.log("Loan request : " + this.loanRequest.status) });
  }



  refresh() {
    this.loanRequestService.getLoanRequestListAll().subscribe(data => {
      this.loanRequests = data;
      this.chRef.detectChanges();
      const table: any = $('table');
      this.dataTable = table.DataTable({
        select: true, retrieve: true, destroy: true, "language": {
          "lengthMenu": "Показать  _MENU_ записи",
          "zeroRecords": "Ничего не найдено",
          "info": "Показ страницы _PAGE_ в _PAGES_",
          "infoEmpty": "Нет доступных записей",
          "infoFiltered": "(Отфильтровано по _MAX_ количеству записей)",
          "search": "Поиск:",
          "paginate": {
            "next": "Следующий",
            "previous": "Назад"
          }
        }
      })
    });




  }

  updateLoanRequest(id) {
    this.router.navigate(['/admin/edit-loan-request', id]);
  }


  logout() {
    this.loginService.logout();
    this.router.navigate(['login']);
  }

  isSuperAdmin(role) {
    if (role == "SUPERADMIN") {
      this.isRoleSuperAdmin = true;
    }
  }

}
