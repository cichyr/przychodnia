import {Patient} from "../patient/patient";
import {Doctor} from "../doctor/doctor";
import {Receptionist} from "../receptionist/receptionist";
import {VisitState} from "./visit-state";
import {ShortPhysicalExamination} from "../examination/short-physical-examination";
import {ShortLaboratoryExamination} from "../examination/short-laboratory-examination";

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
  laboratoryExaminations: ShortLaboratoryExamination[]
  physicalExaminations: ShortPhysicalExamination[]
}
