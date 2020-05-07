package pl.clinic.lab_worker.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pl.clinic.labolratory_examination.model.LaboratoryExamination;
import pl.clinic.user.model.User;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "lab_worker")
@JsonIgnoreProperties(value = {"examinations", "personDetails"})
public class LabWorker extends User {

    @OneToMany(mappedBy = "labWorker")
    private Set<LaboratoryExamination> examinations;

    public Set<LaboratoryExamination> getExaminations() {
        return examinations;
    }

    public void setExaminations(Set<LaboratoryExamination> examinations) {
        this.examinations = examinations;
    }
}
