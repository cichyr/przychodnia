package pl.clinic.app_user.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "app_user")
@IdClass(AppUserId.class)
public class AppUser {
    @Id
    @NotNull
    @Column(name = "employee_id", nullable = false)
    protected Long employeeId;

    @Id
    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "role", nullable = false)
    protected Role role;

    @Size(max = 20)
    @Column(name = "user_name", nullable = false)
    protected String firstName;

    @Size(max = 1024)
    @Column(name = "user_hash", nullable = false)
    protected String userHash;

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getUserHash() {
        return userHash;
    }

    public void setUserHash(String userHash) {
        this.userHash = userHash;
    }
}
