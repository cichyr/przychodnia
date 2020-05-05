package pl.clinic.physical_examination.dto;


import pl.clinic.physical_examination.model.PhysicalExamination;

public class PhysicalExaminationBasic {
    // from examination
    protected Long id;
    protected String name;
    protected Long visitId;


    public PhysicalExaminationBasic(PhysicalExamination examination) {
        this.id = examination.getId();
        this.name = examination.getResult();
        this.visitId=examination.getVisit().getId();

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getVisitId() {
        return visitId;
    }
}
