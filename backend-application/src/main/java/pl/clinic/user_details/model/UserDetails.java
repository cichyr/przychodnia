package pl.clinic.user_details.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "user_details")
public class UserDetails implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    protected Long id;

    @NotBlank
    @Size(max = 20)
    @Column(name = "city", nullable = false)
    protected String city;

    @NotBlank
    @Size(max = 20)
    @Column(name = "streetAddress1", nullable = false)
    protected String streetAddress1;

    @NotBlank
    @Size(max = 20)
    @Column(name = "streetAddress2", nullable = false)
    protected String streetAddress2;

    @NotBlank
    @Size(max = 20)
    @Column(name = "zipCode", nullable = false)
    protected String zipCode;

    @NotBlank
    @Size(max = 20)
    @Column(name = "state", nullable = false)
    protected String state;

    @NotBlank
    @Size(max = 20)
    @Column(name = "contactNumber", nullable = false)
    protected String contactNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() { return city; }

    public void setCity(String firstName) {
        this.city = city;
    }

    public String getStreetAddress1() {
        return streetAddress1;
    }

    public void setStreetAddress1(String streetAddress1) {
        this.streetAddress1 = streetAddress1;
    }

    public String getStreetAddress2() {
        return streetAddress2;
    }

    public void setStreetAddress2(String streetAddress2) {
        this.streetAddress2 = streetAddress2;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
