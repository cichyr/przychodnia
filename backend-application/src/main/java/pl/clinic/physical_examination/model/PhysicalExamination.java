package pl.clinic.physical_examination.model;

import pl.clinic.examination_dictionary.model.ExaminationDictionary;
import pl.clinic.visit.model.Visit;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "physical_examination")
public class PhysicalExamination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    protected Long id;

    @ManyToOne
    @JoinColumn(name = "examination_code_id", referencedColumnName = "code")
    private ExaminationDictionary examinationCode;

    @ManyToOne
    @JoinColumn(name = "visit_id", referencedColumnName = "id", nullable = false)
    private Visit visit;

    @NotBlank
    @Size(max = 1024)
    @Column(name = "result", nullable = false)
    protected String result;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Visit getVisit() {
        return visit;
    }

    public void setVisit(Visit visit) {
        this.visit = visit;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public ExaminationDictionary getExaminationCode() { return examinationCode; }

    public void setExaminationCode(ExaminationDictionary examinationCode) { this.examinationCode = examinationCode; }
}
