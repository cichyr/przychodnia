package pl.clinic.patient.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pl.clinic.user.model.Person;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "patient")
@JsonIgnoreProperties(value = {"personDetails"})
public class Patient extends Person {

    @NotBlank
    @Size(max = 20)
    @Column(name = "pesel_number", nullable = false)
    protected String peselNumber;

    public String getPeselNumber() {
        return peselNumber;
    }

    public void setPeselNumber(String peselNumber) {
        this.peselNumber = peselNumber;
    }
}
