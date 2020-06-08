package pl.clinic.account.controller.dto;

import pl.clinic.account.model.AccountDetails;

public class AccountNewDto {

    private Long id;
    private String role;
    private String username;
    private String password;
    private String status;
    private String licenseCode;
    private String firstName;
    private String lastName;
    private String city;
    private String streetAddress1;
    private String streetAddress2;
    private String zipCode;
    private String region;
    private String contactNumber;


    private AccountNewDto() {

    }

    public Long getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public String getUsername() {
        return username;
    }

    public  String getPassword() { return password;}

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
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

    public String getStatus() {
        return status;
    }

    public String getLicenseCode() {
        return licenseCode;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password){this.password = password;}

    public void setStatus(String status) {
        this.status = status;
    }

    public void setLicenseCode(String licenseCode) {
        this.licenseCode = licenseCode;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
