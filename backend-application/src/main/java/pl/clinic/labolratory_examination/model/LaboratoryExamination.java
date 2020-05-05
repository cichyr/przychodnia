package pl.clinic.labolratory_examination.model;

import pl.clinic.examination_category.model.ExaminationCategory;
import pl.clinic.lab_supervisor.model.LabSupervisor;
import pl.clinic.lab_worker.model.LabWorker;
import pl.clinic.visit.model.Visit;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "laboratory_examination")
public class LaboratoryExamination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    protected Long id;

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "examination_category", referencedColumnName = "code", nullable = false)
    protected ExaminationCategory category;

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "visit_id", referencedColumnName = "id", nullable = false)
    protected Visit visit;

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "state_id", referencedColumnName = "id", nullable = false)
    protected ExaminationState state;

    @ManyToOne
    @JoinColumn(name = "lab_worker_id", referencedColumnName = "id")
    protected LabWorker labWorker;

    @ManyToOne
    @JoinColumn(name = "lab_supervisor_id", referencedColumnName = "id")
    protected LabSupervisor labSupervisor;

    @Size(max = 1024)
    @Column(name = "result")
    protected String result;

    @NotBlank
    @Size(max = 1024)
    @Column(name = "doctor_note", nullable = false)
    protected String doctorNote;

    @Size(max = 1024)
    @Column(name = "supervisor_note")
    protected String supervisorNote;

    @Column(name = "execution_cancellation_date")
    protected LocalDateTime executionCancellationDate;

    @Column(name = "approval_cancellation_date")
    protected LocalDateTime approvalCancellationDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Visit getVisit() {
        return visit;
    }

    public void setVisit(Visit visit) {
        this.visit = visit;
    }

    public ExaminationState getState() {
        return state;
    }

    public void setState(ExaminationState state) {
        this.state = state;
    }

    public LabWorker getLabWorker() {
        return labWorker;
    }

    public void setLabWorker(LabWorker labWorker) {
        this.labWorker = labWorker;
    }

    public LabSupervisor getLabSupervisor() {
        return labSupervisor;
    }

    public void setLabSupervisor(LabSupervisor labSupervisor) {
        this.labSupervisor = labSupervisor;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDoctorNote() {
        return doctorNote;
    }

    public void setDoctorNote(String doctorNote) {
        this.doctorNote = doctorNote;
    }

    public String getSupervisorNote() {
        return supervisorNote;
    }

    public void setSupervisorNote(String supervisorNote) {
        this.supervisorNote = supervisorNote;
    }

    public LocalDateTime getExecutionCancellationDate() {
        return executionCancellationDate;
    }

    public void setExecutionCancellationDate(LocalDateTime executionCancellationDate) { this.executionCancellationDate = executionCancellationDate; }

    public LocalDateTime getApprovalCancellationDate() {
        return approvalCancellationDate;
    }

    public void setApprovalCancellationDate(LocalDateTime approvalCancellationDate) { this.approvalCancellationDate = approvalCancellationDate; }

    public ExaminationCategory getCategory() {
        return category;
    }

    public void setCategory(ExaminationCategory category) {
        this.category = category;
    }
}
