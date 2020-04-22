package pl.clinic.app_user.controller.dto;

import pl.clinic.app_user.model.AccountDetails;

public class AccountDetailsDto {

    private final Long userId;
    private final String role;
    private final String username;
    private final String firstName;
    private final String lastName;
    private final String city;
    private final String streetAddress1;
    private final String streetAddress2;
    private final String zipCode;
    private final String region;
    private final String contactNumber;

    private AccountDetailsDto(AccountDetailsDto.Builder builder) {
        this.userId = builder.userId;
        this.role = builder.role;
        this.username = builder.username;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.city = builder.city;
        this.streetAddress1 = builder.streetAddress1;
        this.streetAddress2 = builder.streetAddress2;
        this.zipCode = builder.zipCode;
        this.region = builder.region;
        this.contactNumber = builder.contactNumber;
    }

    public Long getUserId() {
        return userId;
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


    public static class Builder {

        private final Long userId;
        private final String role;
        private final String username;
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
            this.role = accountDetails.getRole().toString();
            this.username = accountDetails.getUsername();
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


        public AccountDetailsDto build() {
            return new AccountDetailsDto(this);
        }
    }
}
