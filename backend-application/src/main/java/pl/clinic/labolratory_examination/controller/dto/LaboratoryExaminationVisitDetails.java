package pl.clinic.labolratory_examination.controller.dto;

import pl.clinic.labolratory_examination.model.LaboratoryExamination;

import java.time.LocalDateTime;

public class LaboratoryExaminationVisitDetails {

    protected Long id;
    protected String name;
    protected String status;

    public LaboratoryExaminationVisitDetails(LaboratoryExamination examination) {
        this.id = examination.getId();
        this.name = examination.getCategory().getName();
        this.status = examination.getState().getName().toString();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }


}
