export class Error {

    status: number;
    message: string;
    details: string;

    constructor(status: number, message: string, details?: string){
        this.status = status;
        this.message = message;
    }
}