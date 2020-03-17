package pl.clinic.examination_dictionary.model;

import pl.clinic.user_details.model.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ExaminationDictionary {

    @Id
    @Column(name = "code", nullable = false, unique = true)
    protected Long code;

    @NotBlank
    @Size(max = 20)
    @Column(name = "name", nullable = false)
    protected String name;

    @NotNull
    @Column(name = "type", nullable = false)
    @Enumerated(value = EnumType.ORDINAL)
    private ExaminationType type;
}
