export class FieldError {

  field: String
  message: String

  constructor(error: String, message: String) {
    this.field = error;
    this.message = message;
  }
}
