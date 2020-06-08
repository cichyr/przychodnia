package pl.clinic.visit.controller.dto;

import pl.clinic.visit.model.Visit;
import pl.clinic.visit.model.VisitState;

import java.time.LocalDateTime;

public class VisitCreate {
    protected Long receptionistId;
    protected Long doctorId;
    protected Long patientId;
    protected  LocalDateTime appointmentDateTime;
    // doctor


    public VisitCreate(Long receptionistId,Long doctorId,Long patientId, LocalDateTime appointmentDateTime) {
     this.receptionistId = receptionistId;
     this.doctorId = doctorId;
      this.patientId = patientId;
      this.appointmentDateTime = appointmentDateTime;
    }

public Long getReceptionistId() { return receptionistId; }

public Long getDoctorId() { return doctorId; }

public Long getPatientId() { return patientId; }

public LocalDateTime getAppointmentDateTime() {return appointmentDateTime;}



}
