package pl.clinic.labolratory_examination.controller.dto;

import pl.clinic.doctor.model.Doctor;
import pl.clinic.lab_supervisor.model.LabSupervisor;
import pl.clinic.lab_worker.model.LabWorker;
import pl.clinic.labolratory_examination.model.LaboratoryExamination;
import pl.clinic.patient.controller.dto.PatientAndDetails;
import pl.clinic.patient.model.Patient;

import java.time.LocalDateTime;

public class LaboratoryExaminationDetails {
    protected Long id;
    protected String name;
    protected String doctorNote;
    protected String result;
    protected String supervisorNote;
    protected String status;
    protected LocalDateTime creationDate;
    protected LocalDateTime executionCancellationDate;
    protected LocalDateTime approvalCancellationDate;
    protected Patient patient;
    protected Doctor doctor;
    protected LabWorker labWorker;
    protected LabSupervisor labSupervisor;


    public LaboratoryExaminationDetails(LaboratoryExamination examination) {
        this.id = examination.getId();
        this.name = examination.getCategory().getName();
        this.doctorNote = examination.getDoctorNote();
        this.result = examination.getResult();
        this.supervisorNote = examination.getSupervisorNote();
        this.status = examination.getState().getName();
        this.creationDate = examination.getVisit().getFinalizationCancellationDate();
        this.executionCancellationDate = examination.getExecutionCancellationDate();
        this.approvalCancellationDate = examination.getApprovalCancellationDate();
        this.patient = examination.getVisit().getPatient();
        this.doctor = examination.getVisit().getDoctor();
        this.labWorker = examination.getLabWorker();
        this.labSupervisor =examination.getLabSupervisor();
    }

    public Long getId() {
        return id;
    }

    public String getName() { return name; }

    public String getDoctorNote() { return doctorNote; }

    public String getResult() { return result; }

    public String getSupervisorNote() { return supervisorNote; }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public LocalDateTime getExecutionCancellationDate() {
        return executionCancellationDate;
    }

    public LocalDateTime getApprovalCancellationDate() {
        return approvalCancellationDate;
    }

    public Patient getPatient(){ return patient; };

    public Doctor getDoctor(){return doctor;};

    public LabWorker getLabWorker(){return labWorker;};

    public LabSupervisor getLabSupervisor(){return labSupervisor;};
}
