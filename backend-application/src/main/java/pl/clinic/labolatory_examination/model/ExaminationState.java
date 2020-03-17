package pl.clinic.labolatory_examination.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "examination_state")
public class ExaminationState {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    protected Long id;

    @NotBlank
    @Size(max = 20)
    @Column(name = "name", nullable = false)
    protected String name;
}