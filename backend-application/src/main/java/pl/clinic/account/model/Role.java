package pl.clinic.account.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "role")
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role", nullable = false)
    protected Long id;

    @NotBlank
    @Size(max = 20)
    @Column(name = "name", nullable = false)
    protected String name;

    public Long getId() {
        return id;
    }

    public void setId(Long role) {
        this.id = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
