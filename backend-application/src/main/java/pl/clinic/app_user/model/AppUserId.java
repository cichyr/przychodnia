package pl.clinic.app_user.model;

import java.io.Serializable;

public class AppUserId implements Serializable {

    protected Long employeeId;
    protected Role role;

    public AppUserId(Long employeeId, Role role) {
        this.employeeId = employeeId;
        this.role = role;
    }
}
