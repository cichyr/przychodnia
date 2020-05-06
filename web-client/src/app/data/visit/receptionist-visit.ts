import {VisitState} from "./visit-state";

export class ReceptionistVisit {

  patientFirstName: String
  patientLastName: String
  peselNumber: String
  doctorFirstName: String
  doctorLastName: String
  state: VisitState
  registrationDate: Date
  finalizationCancellationDate: Date
}
