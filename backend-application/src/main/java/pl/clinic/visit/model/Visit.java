package pl.clinic.visit.model;

import pl.clinic.doctor.model.Doctor;
import pl.clinic.labolatory_examination.model.LabolatoryExamination;
import pl.clinic.patient.model.Patient;
import pl.clinic.physical_examination.model.PhysicalExamination;
import pl.clinic.receptionist.model.Receptionist;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "visit")
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    protected Long id;

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "receptionist_id", referencedColumnName = "id", nullable = false)
    protected Receptionist receptionist;

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "id", nullable = false)
    protected Doctor doctor;

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id", nullable = false)
    protected Patient patient;

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "state_id", referencedColumnName = "id", nullable = false)
    protected VisitState state;

    @NotBlank
    @Size(max = 1024)
    @Column(name = "description", nullable = false)
    protected String description;

    @Size(max = 1024)
    @Column(name = "diagnose")
    protected String diagnose;

    @NotBlank
    @Column(name = "registration_date", nullable = false)
    protected LocalDateTime registrationDate;

    @Column(name = "finalization_cancellation_date")
    protected LocalDateTime finalizationCancellationDate;

    @OneToMany(mappedBy = "id")
    protected Set<LabolatoryExamination> labolatoryExaminations;

    @OneToMany(mappedBy = "id")
    protected Set<PhysicalExamination> physicalExaminations;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Receptionist getReceptionist() {
        return receptionist;
    }

    public void setReceptionist(Receptionist receptionist) {
        this.receptionist = receptionist;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public VisitState getState() {
        return state;
    }

    public void setState(VisitState state) {
        this.state = state;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDiagnose() {
        return diagnose;
    }

    public void setDiagnose(String diagnose) {
        this.diagnose = diagnose;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) { this.registrationDate = registrationDate; }

    public LocalDateTime getFinalizationCancellationDate() { return finalizationCancellationDate; }

    public void setFinalizationCancellationDate(LocalDateTime finalizationCancellationDate) { this.finalizationCancellationDate = finalizationCancellationDate; }

    public Set<LabolatoryExamination> getLabolatoryExaminations() {
        return labolatoryExaminations;
    }

    public void setLabolatoryExaminations(Set<LabolatoryExamination> labolatoryExaminations) { this.labolatoryExaminations = labolatoryExaminations; }

    public Set<PhysicalExamination> getPhysicalExaminations() {
        return physicalExaminations;
    }

    public void setPhysicalExaminations(Set<PhysicalExamination> physicalExaminations) { this.physicalExaminations = physicalExaminations; }
}
