package pl.clinic.examination_category.controller;

import pl.clinic.labolratory_examination.model.LaboratoryExamination;
import pl.clinic.physical_examination.model.PhysicalExamination;

public class ExaminationBasic {

    // from examination
    protected Long id;
    protected String name;



    public ExaminationBasic(PhysicalExamination examination) {
        this.id = examination.getId();
        this.name = examination.getCategory().getName();
    }

    public ExaminationBasic(LaboratoryExamination examination) {
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
