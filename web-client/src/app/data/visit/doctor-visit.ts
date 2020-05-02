import {VisitState} from "./visit-state";

export class DoctorVisit {

  id: number
  patientFirstName: String
  patientLastName: String
  PESELNumber: String
  state: VisitState
  receptionistFirstName: String
  receptionistLastName: String
  registrationDate: Date               // Dates are passed through HTML as Strings, change if You use different type
  finalizationCancellationDate: Date    // Dates are passed through HTML as Strings, change if You use different type
}
