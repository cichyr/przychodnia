package pl.clinic.lab_supervisor.model;

import pl.clinic.labolatory_examination.model.LabolatoryExamination;
import pl.clinic.user.model.User;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "lab_supervisor")
public class LabSupervisor extends User {

    @OneToMany(mappedBy = "labSupervisor")
    private Set<LabolatoryExamination> examinations;

    public Set<LabolatoryExamination> getExaminations() {
        return examinations;
    }

    public void setExaminations(Set<LabolatoryExamination> examinations) {
        this.examinations = examinations;
    }
}
