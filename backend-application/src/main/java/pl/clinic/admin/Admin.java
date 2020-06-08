package pl.clinic.admin;

import pl.clinic.user.model.User;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "admin")
public class Admin extends User {
}
