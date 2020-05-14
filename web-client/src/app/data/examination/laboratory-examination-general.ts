import { ExaminationState } from './examination-state'

export class LaboratoryExaminationGeneral {
    id: number
    name: string
    status: string
    creationDate: Date
    executionCancellationDate: Date   // Dates are passed through HTML as Strings, change if You use different type
    approvalCancellationDate: Date    // Dates are passed through HTML as Strings, change if You use different type

    // Date formating function
    formatDate(date: string): string {
        switch (date) {
            case 'visit':
                var d = new Date(this.creationDate)
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

    // Constructor
    constructor(id: number, name: string, creationDate: Date, executionCancellationDate: Date, approvalCancellationDate: Date, status: string) {
        this.id = id
        this.name = name
        this.creationDate =  creationDate
        this.executionCancellationDate = executionCancellationDate
        this.approvalCancellationDate = approvalCancellationDate
        this.status = status
    }
}
