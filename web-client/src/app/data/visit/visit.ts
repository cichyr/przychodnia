import {VisitState} from './visit-state'
import {Receptionist} from '../receptionist/receptionist'
import {Doctor} from '../doctor/doctor'
import {Patient} from '../patient/patient'


export class Visit {

    id: number
    receptionistId: Receptionist
    doctorId: Doctor
    patientId: Patient
    stateId: VisitState
    description: String
    diagnose: String
    registrationDate: String                // Dates are passed through HTML as Strings, change if You use different type
    finalizationCancellationDate: Date      // Dates are passed through HTML as Strings, change if You use different type
}
