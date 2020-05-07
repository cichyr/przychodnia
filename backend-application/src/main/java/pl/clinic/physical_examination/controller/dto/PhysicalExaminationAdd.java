package pl.clinic.physical_examination.controller.dto;

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
