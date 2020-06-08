package pl.clinic.patient.controller.dto;

import pl.clinic.error.model.ValidationMessages;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class PatientNewDto {

    @Size(min = 1, max = 20, message = ValidationMessages.SIZE_1_20)
    private String firstName;

    @Size(min = 1, max = 20, message = ValidationMessages.SIZE_1_20)
    private String lastName;

    @Pattern(
            regexp = "\\d{11}",
            message = ValidationMessages.INVALID_PESEL
    )
    private String peselNumber;

    @Size(min = 1, max = 20, message = ValidationMessages.SIZE_1_20)
    private String city;

    @Size(min = 1, max = 60, message = ValidationMessages.SIZE_1_60)
    private String streetAddress1;

    @Size(max = 60, message = ValidationMessages.SIZE_0_60)
    private String streetAddress2;

    @Size(min = 1, max = 20, message = ValidationMessages.SIZE_1_20)
    private String zipCode;

    @Size(min = 1, max = 20, message = ValidationMessages.SIZE_1_20)
    private String region;

    @Pattern(
            regexp = "[+]?[\\d ]{1,20}",
            message = ValidationMessages.INVALID_PHONE
    )
    private String contactNumber;


    private PatientNewDto() {

    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPeselNumber() {
        return peselNumber;
    }

    public String getCity() {
        return city;
    }

    public String getStreetAddress1() {
        return streetAddress1;
    }

    public String getStreetAddress2() {
        return streetAddress2;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getRegion() {
        return region;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPeselNumber(String peselNumber) {
        this.peselNumber = peselNumber;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreetAddress1(String streetAddress1) {
        this.streetAddress1 = streetAddress1;
    }

    public void setStreetAddress2(String streetAddress2) {
        this.streetAddress2 = streetAddress2;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}


