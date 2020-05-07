package pl.clinic.lab_supervisor.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pl.clinic.labolratory_examination.model.LaboratoryExamination;
import pl.clinic.user.model.User;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "lab_supervisor")
@JsonIgnoreProperties(value = {"examinations", "personDetails"})
public class LabSupervisor extends User {

    @OneToMany(mappedBy = "labSupervisor")
    private Set<LaboratoryExamination> examinations;

    public Set<LaboratoryExamination> getExaminations() {
        return examinations;
    }

    public void setExaminations(Set<LaboratoryExamination> examinations) {
        this.examinations = examinations;
    }
}
