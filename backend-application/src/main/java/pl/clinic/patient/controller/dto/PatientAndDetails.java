package pl.clinic.patient.controller.dto;

import pl.clinic.patient.model.Patient;

public class PatientAndDetails {
    // from patient
    protected Long id;
    protected String firstName;
    protected String lastName;
    protected String peselNumber;
    // from userDetails
    protected String city;
    protected String streetAddress1;
    protected String streetAddress2;
    protected String zipCode;
    protected String region;
    protected String contactNumber;

    public PatientAndDetails(Patient patient) {
        this.id = patient.getId();
        this.firstName = patient.getFirstName();
        this.lastName = patient.getLastName();
        this.peselNumber = patient.getPeselNumber();
        this.city = patient.getPersonDetails().getCity();
        this.streetAddress1 = patient.getPersonDetails().getStreetAddress1();
        this.streetAddress2 = patient.getPersonDetails().getStreetAddress2();
        this.zipCode = patient.getPersonDetails().getZipCode();
        this.region = patient.getPersonDetails().getRegion();
        this.contactNumber = patient.getPersonDetails().getContactNumber();
    }

    public Long getId() {
        return id;
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

    public String getStreetAddress1() { return streetAddress1; }

    public String getStreetAddress2() { return streetAddress2; }

    public String getZipCode() {
        return zipCode;
    }

    public String getState() {
        return region;
    }

    public String getContactNumber() {
        return contactNumber;
    }


}
