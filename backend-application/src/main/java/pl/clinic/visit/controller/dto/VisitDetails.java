package pl.clinic.visit.controller.dto;

import pl.clinic.doctor.model.Doctor;
import pl.clinic.labolratory_examination.dto.LaboratoryExaminationBasic;
import pl.clinic.patient.model.Patient;
import pl.clinic.physical_examination.dto.PhysicalExaminationBasic;
import pl.clinic.receptionist.controller.dto.ReceptionistBasic;
import pl.clinic.visit.model.Visit;
import pl.clinic.visit.model.VisitState;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VisitDetails {
    //visit
    protected long visitId;
    // patient
    protected Patient patient;
    // doctor
    protected Doctor doctor;
    // receptionist
    protected ReceptionistBasic receptionist;
    // visit
    protected VisitState state;
    protected String description;
    protected String diagnose;
    protected LocalDateTime registrationDate;
    protected LocalDateTime finalizationCancellationDate;
    protected List<LaboratoryExaminationBasic> labolatoryExaminations;
    protected List<PhysicalExaminationBasic> physicalExaminations;

    public VisitDetails(Visit visit) {
        this.patient = visit.getPatient();
        this.doctor = visit.getDoctor();
        this.receptionist = new ReceptionistBasic(visit.getReceptionist());
        this.state = visit.getState();
        this.description = visit.getDescription();
        this.diagnose = visit.getDiagnose();
        this.registrationDate = visit.getRegistrationDate();
        this.finalizationCancellationDate = visit.getFinalizationCancellationDate();
        this.labolatoryExaminations = new ArrayList<LaboratoryExaminationBasic>();
        this.physicalExaminations = new ArrayList<PhysicalExaminationBasic>();
        visit.getLabolatoryExaminations().forEach(value->this.labolatoryExaminations.add(new LaboratoryExaminationBasic(value)));
        visit.getPhysicalExaminations().forEach(value->this.physicalExaminations.add(new PhysicalExaminationBasic(value)));
    }

    public Patient getPatient() { return patient; }

    public Doctor getDoctor() { return doctor; }

    public ReceptionistBasic getReceptionist() { return receptionist; }

    public VisitState getState() { return state; }

    public String getDescription() { return description; }

    public String getDiagnose() { return diagnose; }

    public LocalDateTime getRegistrationDate() { return registrationDate; }

    public LocalDateTime getFinalizationCancellationDate() { return finalizationCancellationDate; }

    public List<LaboratoryExaminationBasic> getLabolatoryExaminations() { return labolatoryExaminations; }

    public List<PhysicalExaminationBasic> getPhysicalExaminations() { return physicalExaminations; }
}
