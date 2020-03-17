package pl.clinic.visit.model;

import pl.clinic.doctor.model.Doctor;
import pl.clinic.patient.model.Patient;
import pl.clinic.receptionist.model.Receptionist;
import pl.clinic.user_details.model.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "visit")
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    protected Long id;

    @ManyToOne
    @JoinColumn(name = "receptionist_id", referencedColumnName = "id", nullable = true)
    private Receptionist receptionist;

    @ManyToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "id", nullable = true)
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id", nullable = true)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "state_id", referencedColumnName = "id", nullable = false)
    private VisitState state;

    @Size(max = 1024)
    @Column(name = "description", nullable = true)
    protected String description;

    @Size(max = 1024)
    @Column(name = "diagnose", nullable = true)
    protected String diagnose;

    @NotBlank
    @Column(name = "registration_date", nullable = false)
    private Date registrationDate;

    @Column(name = "finalization_cancellation_date", nullable = true)
    private Date finalizationCancellationDate;
}
