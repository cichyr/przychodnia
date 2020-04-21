package pl.clinic.app_user.model;

import java.io.Serializable;

public class AppUserId implements Serializable {

    protected Long employeeId;
    protected Role role;

    public AppUserId() { }

    public AppUserId(Long employeeId, Role role) {
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
