export class Error {

    status: number;
    message: string;
    details: string = 'No additional information.';

    constructor(status?: number, message?: string, details?: string){
        this.status = status;
        this.message = message;
    }
}