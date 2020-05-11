package pl.clinic.labolratory_examination.controller.dto;

import pl.clinic.doctor.model.Doctor;
import pl.clinic.labolratory_examination.model.LaboratoryExamination;
import pl.clinic.physical_examination.model.PhysicalExamination;

import javax.persistence.Column;
import java.time.LocalDateTime;

public class LaboratoryExaminationBasic {

    protected Long id;
    protected String name;
    protected LocalDateTime creationDate;
    protected LocalDateTime executionCancellationDate;
    protected LocalDateTime approvalCancellationDate;
    protected String status;

    public LaboratoryExaminationBasic(LaboratoryExamination examination) {
        this.id = examination.getId();
        this.name = examination.getCategory().getName();
        this.creationDate = examination.getVisit().getFinalizationCancellationDate();
        this.executionCancellationDate = examination.getExecutionCancellationDate();
        this.approvalCancellationDate = examination.getApprovalCancellationDate();
        this.status = examination.getState().getName().toString();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
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

    public String getStatus() {
        return status;
    }

}
