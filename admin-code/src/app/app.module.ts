import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { Routes, RouterModule } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { UserComponent } from './component/user/user.component';
import { LoanTypeComponent } from './component/loan-type/loan-type.component';
import { LoanRequestComponent } from './component/loan-request/loan-request.component';
import { EditLoanTypeComponent } from './component/edit-loan-type/edit-loan-type.component';
import { EditLoanRequestComponent } from './component/edit-loan-request/edit-loan-request.component';
import { LoginComponent } from './component/login/login.component';
import { InterceptorService } from './service/interceptor.service';
import { GuardService } from './service/guard.service';
import { EditUserComponent } from './component/edit-user/edit-user.component';
import { LoanRequestHistoryComponent } from './component/loan-request-history/loan-request-history.component';

const routes: Routes = [
  { path: "login", component: LoginComponent },
  { path: 'admin/user', component: UserComponent, canActivate: [GuardService] },
  { path: 'admin/loan-type', component: LoanTypeComponent, canActivate: [GuardService] },
  { path: 'admin/loan-request', component: LoanRequestComponent, canActivate: [GuardService] },
  { path: 'admin/loan-request-history', component: LoanRequestHistoryComponent, canActivate: [GuardService] },
  { path: 'admin/edit-loan-type/:id', component: EditLoanTypeComponent, canActivate: [GuardService] },
  { path: 'admin/edit-loan-request/:id', component: EditLoanRequestComponent, canActivate: [GuardService] },
  { path: 'admin/edit-user-loan/:id', component: EditUserComponent, canActivate: [GuardService] },
  { path: '**', redirectTo: '/admin/loan-request', pathMatch: 'full' },
]

@NgModule({
  declarations: [
    AppComponent,
    UserComponent,
    LoanTypeComponent,
    LoanRequestComponent,
    EditLoanTypeComponent,
    EditLoanRequestComponent,
    LoginComponent,
    EditUserComponent,
    LoanRequestHistoryComponent,
  ],
  imports: [
    RouterModule.forRoot(routes),
    BrowserModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
  ],
  providers: [{ provide: HTTP_INTERCEPTORS, useClass: InterceptorService, multi: true }],
  bootstrap: [AppComponent]
})
export class AppModule { }
