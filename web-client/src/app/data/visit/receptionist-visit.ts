import {VisitState} from "./visit-state";

export class ReceptionistVisit {

  id: number
  patientFirstName: String
  patientLastName: String
  peselNumber: String
  doctorFirstName: String
  doctorLastName: String
  state: VisitState
  registrationDate: Date
  finalizationCancellationDate: Date


  constructor(id: number, patientFirstName: String, patientLastName: String, peselNumber: String, doctorFirstName: String, doctorLastName: String, state: VisitState, registrationDate: Date, finalizationCancellationDate: Date) {
    this.id = id;
    this.patientFirstName = patientFirstName;
    this.patientLastName = patientLastName;
    this.peselNumber = peselNumber;
    this.doctorFirstName = doctorFirstName;
    this.doctorLastName = doctorLastName;
    this.state = state;
    this.registrationDate = registrationDate;
    this.finalizationCancellationDate = finalizationCancellationDate;
  }
}
