package pl.clinic.physical_examination.controller.dto;

import pl.clinic.doctor.model.Doctor;
import pl.clinic.examination_category.model.ExaminationCategory;
import pl.clinic.patient.model.Patient;
import pl.clinic.physical_examination.model.PhysicalExamination;
import pl.clinic.visit.model.Visit;

import javax.persistence.*;
import javax.validation.constraints.Size;

public class PhysicalExaminationDetails {

    protected Long id;
    protected Long code;
    protected String name;
    protected String result;
    protected Doctor doctor;

    public PhysicalExaminationDetails(PhysicalExamination examination) {
        this.id = examination.getId();
       this.code = examination.getCategory().getCode();
       this.name = examination.getCategory().getName();
       this.result = examination.getResult();
       this.doctor = examination.getVisit().getDoctor();
    }

    public Long getId() {
        return id;
    }

    public Long getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getResult() {
        return result;
    }

    public Doctor getDoctor() { return doctor; }


}
