package pl.clinic.account.model;

import java.io.Serializable;

public class AccountId implements Serializable {

    protected Long employeeId;
    protected Long roleId;

    public AccountId() { }

    public AccountId(Long employeeId, Long roleId) {
        this.employeeId = employeeId;
        this.roleId = roleId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
