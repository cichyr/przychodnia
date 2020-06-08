package pl.clinic.account.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "account")
@IdClass(AccountId.class)
public class Account implements Serializable {

    @Id
    @NotNull
    @Column(name = "employee_id", nullable = false)
    protected Long employeeId;

    @Id
    @NotNull
    @Column(name = "role_id", nullable = false)
    protected Long roleId;

    @NotBlank
    @Size(max = 20)
    @Column(name = "user_name", nullable = false)
    protected String username;

    @NotBlank
    @Size(max = 1024)
    @Column(name = "user_hash", nullable = false)
    protected String hash;

    @NotNull
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    protected AccountStatus status;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String firstName) {
        this.username = firstName;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String userHash) {
        this.hash = userHash;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

}
