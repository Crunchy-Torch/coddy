export class Error {

  status: number;
  message: string;
  details = 'No additional information.';

  constructor(status?: number, message?: string, details?: string) {
    this.status = status;
    this.message = message;
    if(details) {
      this.details = details;
    }
  }
}
