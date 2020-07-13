import { Component, OnInit } from '@angular/core';
import { LoanTypeService } from 'src/app/service/loan-type.service';
import { LoanType } from 'src/app/domain/loan-type';
import { ChangeDetectorRef } from '@angular/core';
import * as $ from 'jquery';
import 'datatables.net';
import 'datatables.net-bs4';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/service/login.service';

@Component({
  selector: 'app-loan-type',
  templateUrl: './loan-type.component.html',
  styleUrls: ['./loan-type.component.css']
})
export class LoanTypeComponent implements OnInit {

  constructor(private loginService:LoginService,private router:Router,private loanTypeService: LoanTypeService, private chRef: ChangeDetectorRef) { }

  loanTypes: LoanType[];
  dataTable: any;
  loanType: LoanType = new LoanType();
  username:string;
  isRoleSuperAdmin: boolean = false;

  ngOnInit(): void {
    this.refresh();
  }

  getLoanTypeById(id) {
    this.loanTypeService.getLoanType(id).subscribe(data => { this.loanType = data; console.log("Loan type : " + this.loanType.name) });
  }

  refresh() {
    this.isSuperAdmin(this.loginService.getUserRole());
    this.loanTypeService.getLoanTypeList().subscribe(data => {
      this.loanTypes = data;
      this.chRef.detectChanges();
      const table: any = $('table');
      this.dataTable = table.DataTable({ select: true, retrieve: true, destroy: true ,"language": {
        "lengthMenu": "Показать  _MENU_ записи",
        "zeroRecords": "Ничего не найдено",
        "info": "Показ страницы _PAGE_ в _PAGES_",
        "infoEmpty": "Нет доступных записей",
        "infoFiltered": "(Отфильтровано по _MAX_ количеству записей)",
        "search":"Поиск:",
          "paginate": {
          "next":       "Следующий",
          "previous":   "Назад"
      }}
    })});
  }

  updateLoanType(id){
    this.router.navigate(['/admin/edit-loan-type',id]);
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