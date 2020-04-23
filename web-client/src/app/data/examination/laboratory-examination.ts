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
    executionCancellationDate: Date   // Dates are passed through HTML as Strings, change if You use different type
    approvalCancellationDate: Date    // Dates are passed through HTML as Strings, change if You use different type

    // Date formating function
    formatDate(date: string) : string {
        switch(date) {
            case 'visit':
                var d = new Date(this.visitId.finalizationCancellationDate)
                break;
            
            case 'execution':
                var d = new Date(this.executionCancellationDate)
                break;

            case 'approval':
                var d = new Date(this.approvalCancellationDate)
                break;
        }
        
        var month = '' + (d.getMonth() + 1)
        var day = '' + d.getDate()
        var year = d.getFullYear()

        if (month.length < 2) 
            month = '0' + month;
        if (day.length < 2) 
            day = '0' + day;

        return [year, month, day].join('-');
    }
}
