import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/service/user.service';
import { User } from 'src/app/domain/user';
import * as $ from 'jquery';
import 'datatables.net';
import 'datatables.net-bs4';
import { ChangeDetectorRef } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/service/login.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  users: User[];
  dataTable: any;
  user: User = new User();
  username: string;
  isRoleSuperAdmin: boolean = false;

  constructor(private userService: UserService, private loginService: LoginService,
    private chRef: ChangeDetectorRef, private router: Router) { }

  ngOnInit(): void {
    this.refresh();
  }

  refresh() {
    this.isSuperAdmin(this.loginService.getUserRole());
    this.userService.getUserList().subscribe(data => {
      this.users = data;
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

  logout() {
    this.loginService.logout();
    this.router.navigate(['login']);
  }

  updateUserLoan(id) {
    this.router.navigate(['/admin/edit-user-loan', id]);
  }

  isSuperAdmin(role) {
    if (role == "SUPERADMIN") {
      this.isRoleSuperAdmin = true;
    }
  }

}
