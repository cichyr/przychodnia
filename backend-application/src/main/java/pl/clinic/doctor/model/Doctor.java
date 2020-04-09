package pl.clinic.doctor.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pl.clinic.user_details.model.UserDetails;
import pl.clinic.visit.model.Visit;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "doctor")
@JsonIgnoreProperties(value = {"userDetails", "visits"})
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    protected Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "user_details_id", referencedColumnName = "id")
    protected UserDetails userDetails;

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
    @Column(name = "license_code", nullable = false)
    protected String licenseCode;

    @OneToMany(mappedBy = "doctor")
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

    public String getLicenseCode() {
        return licenseCode;
    }

    public void setLicenseCode(String licenseCode) {
        this.licenseCode = licenseCode;
    }

    public Set<Visit> getVisits() {
        return visits;
    }

    public void setVisits(Set<Visit> visits) {
        this.visits = visits;
    }
}
