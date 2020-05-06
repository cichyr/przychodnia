package pl.clinic.physical_examination.dto;


import pl.clinic.physical_examination.model.PhysicalExamination;

public class PhysicalExaminationBasic {
    // from examination
    protected Long id;
    protected String name;



    public PhysicalExaminationBasic(PhysicalExamination examination) {
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
