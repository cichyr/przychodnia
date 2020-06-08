
package pl.clinic.patient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;
import pl.clinic.account.controller.dto.AccountDetailsDto;
import pl.clinic.account.controller.dto.AccountNewDto;
import pl.clinic.account.model.*;
import pl.clinic.admin.Admin;
import pl.clinic.common_services.FilteringService;
import pl.clinic.doctor.model.Doctor;
import pl.clinic.lab_supervisor.model.LabSupervisor;
import pl.clinic.lab_worker.model.LabWorker;
import pl.clinic.patient.controller.dto.PatientAndDetails;
import pl.clinic.patient.controller.dto.PatientNewDto;
import pl.clinic.patient.model.Patient;
import pl.clinic.patient.model.PatientRepository;
import pl.clinic.receptionist.model.Receptionist;
import pl.clinic.user.model.PersonDetails;
import pl.clinic.user.model.PersonDetailsRepository;
import pl.clinic.user.model.User;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    PersonDetailsRepository personDetailsRepository;

    @GetMapping(value = "/{patient_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PatientAndDetails> getPatient(@PathVariable Long patient_id) {

        return patientRepository.findById(patient_id)
                .map(value -> ResponseEntity.ok(new PatientAndDetails(value)))
                .orElseGet(() -> ResponseEntity.ok().build());
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Patient>> getPatients(
            @RequestParam(value = "first_name", required = false) String firstName,
            @RequestParam(value = "last_name", required = false) String lastName) {

        List<Patient> patients = new LinkedList<>();
        patientRepository.findAll().forEach(patients::add);

        patients = new FilteringService<>(patients)
                .contains(firstName, Patient::getFirstName)
                .contains(lastName, Patient::getLastName)
                .getFiltered();

        return ResponseEntity.ok(patients);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PatientAndDetails> addUser(
            @Valid @RequestBody PatientNewDto newPatient) {

                Patient patient = new Patient();
        PersonDetails details = new PersonDetails();

        details.setCity(newPatient.getCity());
        details.setContactNumber(newPatient.getContactNumber());
        details.setRegion(newPatient.getRegion());
        details.setStreetAddress1(newPatient.getStreetAddress1());
        details.setStreetAddress2(newPatient.getStreetAddress2());
        details.setZipCode(newPatient.getZipCode());
        details =  personDetailsRepository.save(details);
        //konto pacjenta

        patient.setFirstName(newPatient.getFirstName());
        patient.setLastName(newPatient.getLastName());
        patient.setPeselNumber(newPatient.getPeselNumber());
        patient.setPersonDetails(details);
        patient = patientRepository.save(patient);

        return  ResponseEntity.ok(new PatientAndDetails(patient));
    }
}
