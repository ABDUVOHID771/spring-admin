import { Branch } from './branch';

export class User {

    id: number;

    createdDate: Date;

    updatedDate: Date;
    
    username: string;

    password: string;

    userRole: string;

    status: boolean;

    branch: Branch;
}
