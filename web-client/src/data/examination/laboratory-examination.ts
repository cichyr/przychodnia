import {Visit} from '../visit/visit'
import {ExaminationDictionary} from './examination-dictionary'
import {ExaminationState} from './examination-state'
import {LabWorker} from '../labWorker/lab-worker'
import {LabSupervisor} from '../labSupervisor/lab-supervisor'


export class LaboratoryExamination {
    id: number
    examinationId: ExaminationDictionary
    visitId: Visit
    stateId: ExaminationState
    labWorkerId: LabWorker
    labSupervisorId: LabSupervisor
    result: String
    doctorNote: String
    supervisorNote: String
    executionCancellationDate: String   // Dates are passed through HTML as Strings, change if You use different type
    approvalCancellationDate: String    // Dates are passed through HTML as Strings, change if You use different type
}
