package pl.clinic.patient.model;

import pl.clinic.user_details.model.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    protected Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "user_details_id", referencedColumnName = "id")
    private UserDetails userDetails;

    @NotBlank
    @Size(max = 20)
    @Column(name = "first_name", nullable = false)
    protected String firstName;

    @NotBlank
    @Size(max = 20)
    @Column(name = "last_name", nullable = false)
    protected String lastName;

    @NotBlank
    @Size(max = 20)
    @Column(name = "pesel_number", nullable = false)
    protected String peselNumber;
}
