package pl.clinic.physical_examination.dto;

import pl.clinic.physical_examination.model.PhysicalExamination;

public class PhysicalExaminationAdd {
    // from examination
    protected Long code;
    protected String result;



    public PhysicalExaminationAdd(Long code, String result) {
        this.code = code;
        this.result = result;


    }

    public Long getCode() {
        return code;
    }

    public String getResult() {
        return result;
    }

}
