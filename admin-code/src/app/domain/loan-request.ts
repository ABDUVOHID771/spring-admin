import { Status } from './status.enum';
import { LoanType } from './loan-type';
import { Branch } from './branch';
import { LoanCountingStrategy } from './loan-counting-strategy.enum';
import { UserClass } from './user-class';

export class LoanRequest {

    id: number;

    createdDate: Date;

    updatedDate: Date;

    branch: Branch;

    userRequest: UserClass;

    loanType: LoanType;

    status: Status;

    salaryCardBin: string;

    TIN: string;

    requestedAmount: number;

    countingStrategy: LoanCountingStrategy;

    passportPhoto1:string;

    passportPhoto2:File;

    selfiePhoto:File;

    passportSerie:string;

    passportNumber:string;

}
