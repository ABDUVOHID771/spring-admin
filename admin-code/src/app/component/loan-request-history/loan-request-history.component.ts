import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { LoanRequestHistory } from 'src/app/domain/loan-request-history';
import { LoginService } from 'src/app/service/login.service';
import { Router } from '@angular/router';
import { LoanTypeService } from 'src/app/service/loan-type.service';
import { LoanRequestHistoryService } from 'src/app/service/loan-request-history.service';
import * as $ from 'jquery';
import 'datatables.net';
import 'datatables.net-bs4';

@Component({
  selector: 'app-loan-request-history',
  templateUrl: './loan-request-history.component.html',
  styleUrls: ['./loan-request-history.component.css']
})
export class LoanRequestHistoryComponent implements OnInit {

  constructor(private loginService:LoginService,private router:Router,private loanRequestHistoryService: LoanRequestHistoryService, private chRef: ChangeDetectorRef) { }

  loanRequestHistories: LoanRequestHistory[];
  dataTable: any;
  username:string;
  isRoleSuperAdmin: boolean = false;

  ngOnInit(): void {
    this.refresh();
  }

  refresh() {
    this.isSuperAdmin(this.loginService.getUserRole());
    this.loanRequestHistoryService.getLoanRequestHistoryListAll().subscribe(data => {
      this.loanRequestHistories = data;
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
