package pl.clinic.receptionist.model;

import pl.clinic.user.model.User;
import pl.clinic.visit.model.Visit;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "receptionist")
public class Receptionist extends User {

    @OneToMany(mappedBy = "receptionist")
    protected Set<Visit> visits;

    public Set<Visit> getVisits() {
        return visits;
    }

    public void setVisits(Set<Visit> visits) {
        this.visits = visits;
    }
}
