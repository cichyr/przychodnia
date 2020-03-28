package pl.clinic.examination_dictionary.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "examination_dictionary")
public class ExaminationDictionary {

    @Id
    @Column(name = "code", nullable = false, unique = true)
    protected Long code;

    @NotBlank
    @Size(max = 20)
    @Column(name = "name", nullable = false)
    protected String name;

    @NotNull
    @Column(name = "examination_type", nullable = false)
    @Enumerated(value = EnumType.ORDINAL)
    private ExaminationType type;

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
