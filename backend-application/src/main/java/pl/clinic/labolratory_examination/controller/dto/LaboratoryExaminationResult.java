package pl.clinic.labolratory_examination.controller.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LaboratoryExaminationResult {

    protected String result;
    
    public LaboratoryExaminationResult(String result) {
        this.result = result;
    }

    public LaboratoryExaminationResult()
    {
        super();
    }

    public String getResult() { return result; }

}
