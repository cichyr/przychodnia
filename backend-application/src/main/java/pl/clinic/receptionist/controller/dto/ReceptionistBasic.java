package pl.clinic.receptionist.controller.dto;

import pl.clinic.patient.model.Patient;
import pl.clinic.receptionist.model.Receptionist;

public class ReceptionistBasic {
        // from receptionist
        protected Long id;
        protected String firstName;
        protected String lastName;


        public ReceptionistBasic(Receptionist receptionist) {
            this.id = receptionist.getId();
            this.firstName = receptionist.getFirstName();
            this.lastName = receptionist.getLastName();

        }

        public Long getId() {
            return id;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

}


