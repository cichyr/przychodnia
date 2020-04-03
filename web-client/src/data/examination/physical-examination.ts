import {Visit} from '../visit/visit'
import {ExaminationDictionary} from './examination-dictionary'


export class PhysicalExamination {
    id: number
    examinationId: ExaminationDictionary
    visitId: Visit
    result: String
}
