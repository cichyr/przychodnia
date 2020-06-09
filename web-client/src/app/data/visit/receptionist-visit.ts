import {VisitState} from "./visit-state";
import {VisitDetails} from "./visit-details";

export class ReceptionistVisit {

  public id: number
  public patientFirstName: String
  public patientLastName: String
  public peselNumber: String
  public doctorFirstName: String
  public doctorLastName: String
  public state: VisitState
  public registrationDate: Date
  public finalizationCancellationDate: Date
  public appointmentDateTime: Date


constructor(id: number, patientFirstName: String, patientLastName: String, peselNumber: String, doctorFirstName: String, doctorLastName: String, state: VisitState, registrationDate: Date, finalizationCancellationDate: Date, appointmentDateTime: Date)
{
  this.id = id;
  this.patientFirstName = patientFirstName;
  this.patientLastName = patientLastName;
  this.peselNumber = peselNumber;
  this.doctorFirstName = doctorFirstName;
  this.doctorLastName = doctorLastName;
  this.state = state;
  if (registrationDate != null)
    this.registrationDate = registrationDate;
  if (finalizationCancellationDate != null)
    this.finalizationCancellationDate = finalizationCancellationDate;
  if (appointmentDateTime != null)
    this.appointmentDateTime = appointmentDateTime;

}

public setData(visit: VisitDetails): void
{
  this.id = visit.visitId;
  this.patientFirstName = visit.patient.firstName;
  this.patientLastName = visit.patient.lastName;
  this.peselNumber = visit.patient.peselNumber;
  this.doctorFirstName = visit.doctor.firstName;
  this.doctorLastName = visit.doctor.lastName;
  this.state = visit.state;
  if (visit.registrationDate != null)
    this.registrationDate = visit.registrationDate;
  if (visit.finalizationCancellationDate != null)
    this.finalizationCancellationDate = visit.finalizationCancellationDate;
}

}


