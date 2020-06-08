import {FieldNames} from "./field-names.enum";
import {FieldNamesPL} from "./field-names-pl.enum";
import {MessagesPL} from "./messages-pl.enum";
import {Messages} from "./messages.enum";

export class FieldError {

  field: String
  message: String

  constructor(field: String, message: String) {
    this.field = field;
    this.message = message;
  }

  translate() {
    switch (this.field) {
      case FieldNames.LICENSE_CODE.toString():
        this.field = FieldNamesPL.LICENSE_CODE.toString()
        break
      case FieldNames.USERNAME.toString():
        this.field = FieldNamesPL.USERNAME.toString()
        break
      case FieldNames.PASSWORD.toString():
        this.field = FieldNamesPL.PASSWORD.toString()
        break
      case FieldNames.FIRST_NAME.toString():
        this.field = FieldNamesPL.FIRST_NAME.toString()
        break
      case FieldNames.LAST_NAME.toString():
        this.field = FieldNamesPL.LAST_NAME.toString()
        break
      case FieldNames.CITY.toString():
        this.field = FieldNamesPL.CITY.toString()
        break
      case FieldNames.STREET_ADDRESS_1.toString():
        this.field = FieldNamesPL.STREET_ADDRESS_1.toString()
        break
      case FieldNames.STREET_ADDRESS_2.toString():
        this.field = FieldNamesPL.STREET_ADDRESS_2.toString()
        break
      case FieldNames.ZIP_CODE.toString():
        this.field = FieldNamesPL.ZIP_CODE.toString()
        break
      case FieldNames.REGION.toString():
        this.field = FieldNamesPL.REGION.toString()
        break
      case FieldNames.CONTACT_NUMBER.toString():
        this.field = FieldNamesPL.CONTACT_NUMBER.toString()
        break
      case FieldNames.PESEL.toString():
        this.field = FieldNamesPL.PESEL.toString()
        break
    }

    switch (this.message) {
      case Messages.NOT_BLANK.toString():
        this.message = MessagesPL.NOT_BLANK.toString()
        break
      case Messages.SIZE_1_20.toString():
        this.message = MessagesPL.SIZE_1_20.toString()
        break
      case Messages.SIZE_0_60.toString():
        this.message = MessagesPL.SIZE_0_60.toString()
        break
      case Messages.SIZE_1_60.toString():
        this.message = MessagesPL.SIZE_1_60.toString();
        break
      case Messages.INVALID_PHONE.toString():
        this.message = MessagesPL.INVALID_PHONE.toString()
        break
      case Messages.USERNAME_UNIQUE.toString():
        this.message = MessagesPL.USERNAME_UNIQUE.toString()
        break
      case Messages.INVALID_PESEL.toString():
        this.message = MessagesPL.INVALID_PESEL.toString()
        break
    }
  }
}
