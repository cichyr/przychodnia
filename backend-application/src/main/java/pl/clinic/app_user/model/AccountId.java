package pl.clinic.app_user.model;

import java.io.Serializable;

public class AccountId implements Serializable {

    protected Long employeeId;
    protected Role role;

    public AccountId() { }

    public AccountId(Long employeeId, Role role) {
        this.employeeId = employeeId;
        this.role = role;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
