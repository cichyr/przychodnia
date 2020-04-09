package pl.clinic.patient.model;

import pl.clinic.user_details.model.UserDetails;
import pl.clinic.visit.model.Visit;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    protected Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "userDetailsId", referencedColumnName = "id")
    protected UserDetails userDetails;

    @NotBlank
    @Size(max = 20)
    @Column(name = "firstName", nullable = false)
    protected String firstName;

    @NotBlank
    @Size(max = 20)
    @Column(name = "lastName", nullable = false)
    protected String lastName;

    @NotBlank
    @Size(max = 20)
    @Column(name = "peselNumber", nullable = false)
    protected String peselNumber;

    @OneToMany(mappedBy = "patient")
    protected Set<Visit> visits;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPeselNumber() {
        return peselNumber;
    }

    public void setPeselNumber(String peselNumber) {
        this.peselNumber = peselNumber;
    }

    public Set<Visit> getVisits() {
        return visits;
    }

    public void setVisits(Set<Visit> visits) {
        this.visits = visits;
    }
}
