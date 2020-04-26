package pl.clinic.doctor.model;

import pl.clinic.user.model.User;
import pl.clinic.visit.model.Visit;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "doctor")
public class Doctor extends User {

    @OneToMany(mappedBy = "doctor")
    protected Set<Visit> visits;

    public Set<Visit> getVisits() {
        return visits;
    }

    public void setVisits(Set<Visit> visits) {
        this.visits = visits;
    }


}
