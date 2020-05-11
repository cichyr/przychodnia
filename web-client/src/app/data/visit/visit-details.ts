import {Patient} from "../patient/patient";
import {Doctor} from "../doctor/doctor";
import {Receptionist} from "../receptionist/receptionist";
import {VisitState} from "./visit-state";
import {LaboratoryExamination} from "../examination/laboratory-examination";
import {ShortPhysicalExamination} from "../examination/short-physical-examination";

export class VisitDetails {

  visitId: number
  patient: Patient
  doctor: Doctor
  receptionist: Receptionist
  state: VisitState
  description: String
  diagnose: String
  registrationDate: Date
  finalizationCancellationDate: Date
  laboratoryExaminations: LaboratoryExamination[]
  physicalExaminations: ShortPhysicalExamination[]
}
