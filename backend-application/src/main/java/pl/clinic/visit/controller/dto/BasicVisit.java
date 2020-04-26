package pl.clinic.visit.controller.dto;

import pl.clinic.visit.model.VisitState;

import java.time.LocalDateTime;

public class BasicVisit {
    // patient
    protected String patientFirstName;
    protected String patientLastName;
    // doctor
    protected String doctorFirstName;
    protected String doctorLastName;
    // receptionist
    protected String receptionistFirstName;
    protected String receptionistLastName;
    // visit
    protected VisitState state;
    protected LocalDateTime registrationDate;

    public BasicVisit(String patientFirstName, String patientLastName, String doctorFirstName, String doctorLastName, String receptionistFirstName, String receptionistLastName, VisitState state, LocalDateTime registrationDate) {
        this.patientFirstName = patientFirstName;
        this.patientLastName = patientLastName;
        this.doctorFirstName = doctorFirstName;
        this.doctorLastName = doctorLastName;
        this.receptionistFirstName = receptionistFirstName;
        this.receptionistLastName = receptionistLastName;
        this.state = state;
        this.registrationDate = registrationDate;
    }

    public String getPatientFirstName() { return patientFirstName; }

    public String getPatientLastName() { return patientLastName; }

    public String getDoctorFirstName() { return doctorFirstName; }

    public String getDoctorLastName() { return doctorLastName; }

    public String getReceptionistFirstName() { return receptionistFirstName; }

    public String getReceptionistLastName() { return receptionistLastName; }

    public VisitState getState() { return state; }

    public LocalDateTime getRegistrationDate() { return registrationDate; }
}
