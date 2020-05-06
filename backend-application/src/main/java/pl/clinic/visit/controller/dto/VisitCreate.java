package pl.clinic.visit.controller.dto;

import pl.clinic.visit.model.Visit;
import pl.clinic.visit.model.VisitState;

import java.time.LocalDateTime;

public class VisitCreate {
    protected Long receptionistId;
    protected Long doctorId;
    protected Long patientId;
    // doctor


    public VisitCreate(Long receptionistId,Long doctorId,Long patientId) {
     this.receptionistId = receptionistId;
     this.doctorId = doctorId;
      this.patientId = patientId;
    }

public Long getReceptionistId() { return receptionistId; }

public Long getDoctorId() { return doctorId; }

public Long getPatientId() { return patientId; }



}
