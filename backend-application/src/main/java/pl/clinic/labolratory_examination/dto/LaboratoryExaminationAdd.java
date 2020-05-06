package pl.clinic.labolratory_examination.dto;

public class LaboratoryExaminationAdd {
    // from examination
    protected Long code;
    protected String doctorNotes;



    public LaboratoryExaminationAdd(Long code, String doctorNotes) {
        this.code = code;
        this.doctorNotes = doctorNotes;


    }

    public Long getCode() {
        return code;
    }

    public String getDoctorNotes() {
        return doctorNotes;
    }
}
