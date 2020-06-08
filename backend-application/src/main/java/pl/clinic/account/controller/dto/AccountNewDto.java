package pl.clinic.account.controller.dto;

import pl.clinic.error.model.ValidationMessages;
import pl.clinic.util.validator.UniqueUsername;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class AccountNewDto {

    private String role;

    @NotBlank(message = ValidationMessages.NOT_BLANK)
    @UniqueUsername(message = ValidationMessages.USERNAME_UNIQUE)
    private String username;

    @NotBlank(message = ValidationMessages.NOT_BLANK)
    private String password;

    private String status;

    @Size(min = 1, max = 20, message = ValidationMessages.SIZE_1_20)
    private String licenseCode;

    @Size(min = 1, max = 20, message = ValidationMessages.SIZE_1_20)
    private String firstName;

    @Size(min = 1, max = 20, message = ValidationMessages.SIZE_1_20)
    private String lastName;

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


    private AccountNewDto() {

    }

    public String getRole() {
        return role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

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

    public void setRole(String role) {
        this.role = role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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
