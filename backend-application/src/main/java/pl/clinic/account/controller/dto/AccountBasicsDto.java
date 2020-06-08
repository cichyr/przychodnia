package pl.clinic.account.controller.dto;

import pl.clinic.account.model.AccountDetails;
import pl.clinic.account.model.AccountStatus;
import pl.clinic.account.model.Role;
import pl.clinic.account.model.Account;

public class AccountBasicsDto {
    private Long id;
    private Role role;
    private String username;
    private String firstName;
    private String lastName;
    private AccountStatus status;

    public AccountBasicsDto(AccountDetails account) {
        this.id = account.getId();
        this.username = account.getUsername();
        this.role = account.getRole();
        this.status = account.getStatus();
    }

    public Long getId() {
        return id;
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

    public Role getRole() {
        return role;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
