package pl.clinic.receptionist.controller.dto;

import pl.clinic.visit.model.Visit;
import pl.clinic.visit.model.VisitState;

import java.util.Date;
// id wizyty, imię i nazwisko pacjenta, imię i nazwisko lekarza, status, datę rejestracji.
public class VisitDto {
    // patient
    protected String patientFirstName;
    protected String patientLastName;
    // doctor
    protected String doctorFirstName;
    protected String doctorLastName;
    // visit
    protected VisitState state;
    protected Date registrationDate;

    public VisitDto(Visit visit) {
        this.patientFirstName = visit.getPatient().getFirstName();
        this.patientLastName = visit.getPatient().getLastName();
        this.doctorFirstName = visit.getDoctor().getFirstName();
        this.doctorLastName = visit.getDoctor().getLastName();
        this.state = visit.getState();
        this.registrationDate = visit.getRegistrationDate();
    }

    public String getPatientFirstName() {
        return patientFirstName;
    }

    public String getPatientLastName() {
        return patientLastName;
    }

    public String getDoctorFirstName() {
        return doctorFirstName;
    }

    public String getDoctorLastName() {
        return doctorLastName;
    }

    public VisitState getState() {
        return state;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }
}
