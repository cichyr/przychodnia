package pl.clinic.visit.controller.dto;

public class Interview {

    protected String description;
    protected String diagnose;

    // doctor


    public Interview( String description,  String diagnose) {
        this.description = description;
        this.diagnose = diagnose;
    }

    public String getDescription() { return description; }

    public String getDiagnose() { return diagnose; }


}
