package pl.clinic.account.controller.dto;

import pl.clinic.account.model.AccountDetails;
import pl.clinic.error.model.ValidationMessages;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class AccountDetailsDto {

    private Long id;
    private String role;
    private String username;
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

    public AccountDetailsDto() {
    }

    private AccountDetailsDto(AccountDetailsDto.Builder builder) {
        this.id = builder.userId;
        this.role = builder.role;
        this.username = builder.username;
        this.status = builder.status;
        this.licenseCode = builder.licenseCode;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.city = builder.city;
        this.streetAddress1 = builder.streetAddress1;
        this.streetAddress2 = builder.streetAddress2;
        this.zipCode = builder.zipCode;
        this.region = builder.region;
        this.contactNumber = builder.contactNumber;
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

    public static class Builder {

        private final Long userId;
        private final String role;
        private final String username;
        private final String status;
        private String licenseCode;
        private String firstName;
        private String lastName;
        private String city;
        private String streetAddress1;
        private String streetAddress2;
        private String zipCode;
        private String region;
        private String contactNumber;

        public Builder(AccountDetails accountDetails) {
            this.userId = accountDetails.getId();
            this.role = accountDetails.getRole().getName();
            this.username = accountDetails.getUsername();
            this.status = accountDetails.getStatus().name();
        }

        public AccountDetailsDto.Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public AccountDetailsDto.Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public AccountDetailsDto.Builder city(String city) {
            this.city = city;
            return this;
        }

        public AccountDetailsDto.Builder streetAddress1(String streetAddress1) {
            this.streetAddress1 = streetAddress1;
            return this;
        }

        public AccountDetailsDto.Builder streetAddress2(String streetAddress2) {
            this.streetAddress2 = streetAddress2;
            return this;
        }

        public AccountDetailsDto.Builder zipCode(String zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public AccountDetailsDto.Builder region(String region) {
            this.region = region;
            return this;
        }

        public AccountDetailsDto.Builder contactNumber(String contactNumber) {
            this.contactNumber = contactNumber;
            return this;
        }

        public AccountDetailsDto.Builder licenseCode(String licenseCode) {
            this.licenseCode = licenseCode;
            return this;
        }


        public AccountDetailsDto build() {
            return new AccountDetailsDto(this);
        }
    }
}
