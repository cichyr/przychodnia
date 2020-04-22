package pl.clinic.doctor.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pl.clinic.user.model.User;
import pl.clinic.visit.model.Visit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "doctor")
@JsonIgnoreProperties(value = {"userDetails", "visits"})
public class Doctor extends User {

    @NotBlank
    @Size(max = 20)
    @Column(name = "license_code", nullable = false)
    protected String licenseCode;

    @OneToMany(mappedBy = "doctor")
    protected Set<Visit> visits;


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
