import { Component, OnInit } from '@angular/core';
import { LoanRequest } from 'src/app/domain/loan-request';
import { Status } from 'src/app/domain/status.enum';
import { ActivatedRoute, Router } from '@angular/router';
import { LoanRequestService } from 'src/app/service/loan-request.service';
import { LoginService } from 'src/app/service/login.service';
import { LoanRequestHistory } from 'src/app/domain/loan-request-history';
import { LoanRequestHistoryService } from 'src/app/service/loan-request-history.service';

@Component({
  selector: 'app-edit-loan-request',
  templateUrl: './edit-loan-request.component.html',
  styleUrls: ['./edit-loan-request.component.css'],
})
export class EditLoanRequestComponent implements OnInit {

  id: number;
  loanRequest: LoanRequest;
  status: Status;
  statusValues: Status[] = [];
  username: string;
  isRoleSuperAdmin: boolean = false;
  userLoanRequestHistory: LoanRequestHistory = new LoanRequestHistory();
  imgBack: any;
  imgFront: any;
  urlBack:any;
  urlFront:any;
  urlSelfie:any;

  constructor(private loanRequestHistoryService: LoanRequestHistoryService,
    private loanRequestService: LoanRequestService,
    private loginService: LoginService, private activatedRoute: ActivatedRoute, private loanTypeService: LoanRequestService, private router: Router) { }

  ngOnInit(): void {

    this.isSuperAdmin(this.loginService.getUserRole());
    this.id = this.activatedRoute.snapshot.params['id'];

    this.loanRequest = new LoanRequest();
    this.loanTypeService.getLoanRequest(this.id).subscribe(data => {
      this.loanRequest = data;

      this.urlBack = "2020-06-30/AA0554332/back.jpeg";
      this.urlFront = "2020-06-30/AA0554332/front.jpeg";
      this.urlSelfie = "2020-06-30/AA0554332/selfie.jpeg";

      this.loanRequestService.getImage(this.urlBack).subscribe(data => {
        this.createImageFromBlobForBack(data);
      });

      this.loanRequestService.getImage(this.urlFront).subscribe(data => {
        this.createImageFromBlobForFront(data);
      });

      this.loanRequestService.getImage(this.urlSelfie).subscribe(data => {
        this.createImageFromBlobForSelfie(data);
      });

      if (this.loanRequest.status.toString() === "ACCEPT") {

        this.statusValues.push(Status.ACCEPT, Status.PROCESS, Status.FINISH, Status.CANCELLED);
      } else if (this.loanRequest.status.toString() === "PROCESS") {
        this.statusValues.push(Status.PROCESS, Status.FINISH, Status.CANCELLED);
      } else if (this.loanRequest.status.toString() === "FINISH") {
        this.statusValues.push(Status.FINISH);
      } else if (this.loanRequest.status.toString() === "CANCELLED") {
        this.statusValues.push(Status.CANCELLED);
      }
      this.userLoanRequestHistory.previousStatus = this.loanRequest.status;
    });

  }

  edit(id) {
    this.loanTypeService.updateLoanRequest(this.id, this.loanRequest);
    let phone = this.loanRequest.userRequest.phone;
    let text = "DAVR BANK SMS : " + this.loanRequest.status;
    this.loanRequestService.sendMessage(phone, text);
    // this.userLoanRequestHistory.loanUser = parseInt(this.loginService.getUserId());
    this.userLoanRequestHistory.loanRequest = this.loanRequest;
    this.userLoanRequestHistory.changedStatus = this.loanRequest.status;
    this.loanRequestHistoryService.addHistory(this.userLoanRequestHistory);
    this.loanTypeService.updateLoanRequest(this.id, this.loanRequest).subscribe(data=>this.router.navigate(['/admin/loan-request']));
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

  imageToBack: any;
  imageToFront: any;
  imageToSelfie:any;

  createImageFromBlobForBack(image: Blob) {
    let reader = new FileReader();
    reader.addEventListener("load", () => {
      this.imageToBack = reader.result;
    }, false);

    if (image) {
      reader.readAsDataURL(image);
    }
  }

  createImageFromBlobForFront(image: Blob) {
    let reader = new FileReader();
    reader.addEventListener("load", () => {
      this.imageToFront = reader.result;
    }, false);

    if (image) {
      reader.readAsDataURL(image);
    }
  }
  
  createImageFromBlobForSelfie(image: Blob) {
    let reader = new FileReader();
    reader.addEventListener("load", () => {
      this.imageToSelfie = reader.result;
    }, false);

    if (image) {
      reader.readAsDataURL(image);
    }
  }
}
