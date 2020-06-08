package pl.clinic.user.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "person_details")
public class PersonDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    protected Long id;

  //  @NotBlank
    @Size(max = 20)
    @Column(name = "city", nullable = false)
    protected String city;

  //  @NotBlank
    @Size(max = 64)
    @Column(name = "street_address1", nullable = false)
    protected String streetAddress1;

    @Size(max = 64)
    @Column(name = "street_address2")
    protected String streetAddress2;

    //@NotBlank
    @Size(max = 20)
    @Column(name = "zip_code", nullable = false)
    protected String zipCode;

   // @NotBlank
    @Size(max = 20)
    @Column(name = "region", nullable = false)
    protected String region;

   // @NotBlank
    @Size(max = 20)
    @Column(name = "contact_number", nullable = false)
    protected String contactNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() { return city; }

    public void setCity(String city) { this.city = city; }

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

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getRegion() { return region; }

    public void setRegion(String region) { this.region = region; }
}
