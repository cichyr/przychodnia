package pl.clinic.physical_examination.model;

import pl.clinic.examination_category.model.ExaminationCategory;
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

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "examination_category", referencedColumnName = "code", nullable = false)
    protected ExaminationCategory category;

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "visit_id", referencedColumnName = "id", nullable = false)
    protected Visit visit;

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

    public ExaminationCategory getCategory() { return category; }

    public void setCategory(ExaminationCategory category) {
        this.category = category;
    }
}
