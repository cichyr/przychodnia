import {Visit} from '../visit/visit'
import {ExaminationDictionary} from './examination-dictionary'
import {Doctor} from '../doctor/doctor'


// export class PhysicalExamination {
//     id: number
//     examinationId: ExaminationDictionary
//     visitId: Visit
//     result: String
// }

export class PhysicalExamination {
    id: number
    code: number
    name: String
    result: String
    doctor: Doctor
}