export enum examinationType {
    Laboratory,
    Physical
}


export class ExaminationDictionary {
    id: number
    name: String
    type: examinationType
}
