package pl.clinic.account.model;

import javax.persistence.*;
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
    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "role", nullable = false)
    protected Role role;

    @Size(max = 20)
    @Column(name = "user_name", nullable = false)
    protected String username;

    @Size(max = 1024)
    @Column(name = "user_hash", nullable = false)
    protected String hash;

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
}
