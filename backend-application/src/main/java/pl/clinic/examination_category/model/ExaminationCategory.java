package pl.clinic.examination_category.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "examination_category")
public class ExaminationCategory {

    @Id
    @Column(name = "code", nullable = false, unique = true)
    protected Long code;


    @Size(max = 128)
    @Column(name = "name", nullable = false)
    protected String name;


    @Column(name = "type", nullable = false)
    @Enumerated(value = EnumType.STRING)
    protected ExaminationType type;

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ExaminationType getType() {
        return type;
    }

    public void setType(ExaminationType type) {
        this.type = type;
    }
}
