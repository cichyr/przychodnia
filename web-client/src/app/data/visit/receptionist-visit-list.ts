import {VisitState} from "./visit-state";

export class ReceptionistVisitList {

  patientFirstName: String
  patientLastName: String
  peselNumber: String
  docotrFirstName: String
  docotrLastname: String
  receptionistFirstName: String
  receptionistLastName: String
  state: VisitState
  registrationDate: Date
  finalizationCancellationDate: Date
}
