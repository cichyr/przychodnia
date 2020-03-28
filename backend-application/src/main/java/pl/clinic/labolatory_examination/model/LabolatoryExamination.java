package pl.clinic.labolatory_examination.model;

import pl.clinic.examination_dictionary.model.ExaminationDictionary;
import pl.clinic.lab_supervisor.model.LabSupervisor;
import pl.clinic.lab_worker.model.LabWorker;
import pl.clinic.visit.model.Visit;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "labolatory_examination")
public class LabolatoryExamination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    protected Long id;

    @ManyToOne
    @JoinColumn(name = "examination_code_id", referencedColumnName = "code")
    private ExaminationDictionary examinationCode;

    @ManyToOne
    @JoinColumn(name = "visit_id", referencedColumnName = "id", nullable = false)
    private Visit visit;

    @ManyToOne
    @JoinColumn(name = "state_id", referencedColumnName = "id", nullable = false)
    private ExaminationState state;

    @ManyToOne
    @JoinColumn(name = "lab_worker_id", referencedColumnName = "id", nullable = true)
    private LabWorker labWorker;

    @ManyToOne
    @JoinColumn(name = "lab_supervisor_id", referencedColumnName = "id", nullable = true)
    private LabSupervisor labSupervisor;

    @Size(max = 1024)
    @Column(name = "result", nullable = true)
    protected String result;

    @Size(max = 1024)
    @Column(name = "doctor_note", nullable = true)
    protected String doctorNote;

    @Size(max = 1024)
    @Column(name = "supervisor_note", nullable = true)
    protected String supervisorNote;

    @Column(name = "execution_cancellation_date", nullable = false)
    private Date executionCancellationDate;

    @Column(name = "approval_cancellation_date", nullable = true)
    private Date approvalCancellationDate;

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

    public Date getExecutionCancellationDate() {
        return executionCancellationDate;
    }

    public ExaminationDictionary getExaminationCode() { return examinationCode; }

    public void setExamination(ExaminationDictionary examinationCode) { this.examinationCode = examinationCode; }

    public void setExecutionCancellationDate(Date executionCancellationDate) {
        this.executionCancellationDate = executionCancellationDate;
    }

    public Date getApprovalCancellationDate() {
        return approvalCancellationDate;
    }

    public void setApprovalCancellationDate(Date approvalCancellationDate) {
        this.approvalCancellationDate = approvalCancellationDate;
    }
}
