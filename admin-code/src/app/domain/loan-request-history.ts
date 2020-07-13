import { LoanRequest } from './loan-request';
import { User } from './user';
import { Status } from './status.enum';

export class LoanRequestHistory {

    loanRequest: LoanRequest;
    loanUser: number;
    previousStatus: Status;
    changedStatus: Status;

}
