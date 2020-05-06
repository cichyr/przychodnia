package pl.clinic.labolratory_examination.dto;

import pl.clinic.labolratory_examination.model.LaboratoryExamination;

public class LaboratoryExaminationBasic {
    // from patient
    protected Long id;
    protected String name;


    public LaboratoryExaminationBasic(LaboratoryExamination examination) {
        this.id = examination.getId();
        this.name = examination.getCategory().getName();

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }


}
