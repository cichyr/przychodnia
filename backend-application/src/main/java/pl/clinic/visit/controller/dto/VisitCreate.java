package pl.clinic.visit.controller.dto;

import pl.clinic.visit.model.Visit;
import pl.clinic.visit.model.VisitState;

import java.time.LocalDateTime;

public class VisitCreate {
    protected Long receprionistId;
    protected Long doctorId;
    protected Long patientId;
    // doctor


    public VisitCreate(Visit visit) {
     //   this.patientFirstName = visit.getPatient().getFirstName();
      //  this.patientLastName = visit.getPatient().getLastName();
       // this.peselNumber = visit.getPatient().getPeselNumber();

    }

 //   public String getPatientFirstName() { return patientFirstName; }

   // public String getPatientLastName() { return patientLastName; }

   // public String getDoctorFirstName() { return doctorFirstName; }



}
