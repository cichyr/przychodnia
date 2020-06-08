package pl.clinic.visit.controller.dto;

import pl.clinic.visit.model.Visit;
import pl.clinic.visit.model.VisitState;

import java.time.LocalDateTime;

public class BasicVisit {
    // patient
    protected String patientFirstName;
    protected String patientLastName;
    protected String peselNumber;
    // doctor
    protected String doctorFirstName;
    protected String doctorLastName;
    // receptionist
    protected String receptionistFirstName;
    protected String receptionistLastName;
    // visit
    protected Long id;
    protected VisitState state;
    protected LocalDateTime registrationDate;
    protected LocalDateTime finalizationCancellationDate;
    protected  LocalDateTime appointmentDateTime;

    public BasicVisit(Visit visit) {
        this.patientFirstName = visit.getPatient().getFirstName();
        this.patientLastName = visit.getPatient().getLastName();
        this.peselNumber = visit.getPatient().getPeselNumber();
        this.doctorFirstName = visit.getDoctor().getFirstName();
        this.doctorLastName = visit.getDoctor().getLastName();
        this.receptionistFirstName = visit.getReceptionist().getFirstName();
        this.receptionistLastName = visit.getReceptionist().getLastName();
        this.id = visit.getId();
        this.state = visit.getState();
        this.registrationDate = visit.getRegistrationDate();
        this.finalizationCancellationDate = visit.getFinalizationCancellationDate();
        this.appointmentDateTime = visit.getAppointmentDateTime();
    }

    public String getPatientFirstName() { return patientFirstName; }

    public String getPatientLastName() { return patientLastName; }

    public String getDoctorFirstName() { return doctorFirstName; }

    public String getDoctorLastName() { return doctorLastName; }

    public String getReceptionistFirstName() { return receptionistFirstName; }

    public String getReceptionistLastName() { return receptionistLastName; }

    public VisitState getState() { return state; }

    public LocalDateTime getRegistrationDate() { return registrationDate; }

    public String getPeselNumber() { return peselNumber; }

    public LocalDateTime getFinalizationCancellationDate() { return finalizationCancellationDate; }

    public LocalDateTime getAppointmentDateTime() {return  appointmentDateTime;}

    public Long getId() { return id; }
}
