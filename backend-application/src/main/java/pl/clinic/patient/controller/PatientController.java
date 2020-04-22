
package pl.clinic.patient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.clinic.common_services.FilteringService;
import pl.clinic.patient.controller.dto.PatientAndDetailsDto;
import pl.clinic.patient.model.Patient;
import pl.clinic.patient.model.PatientRepository;

import java.util.*;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    PatientRepository patientRepository;

    @GetMapping(value = "/{patient_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PatientAndDetailsDto> getPatient(@PathVariable Long patient_id) {

        return patientRepository.findById(patient_id)
                .map(value -> ResponseEntity.ok(new PatientAndDetailsDto(value)))
                .orElseGet(() -> ResponseEntity.ok().build());
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Patient> getPatients(
            @RequestParam(value = "first_name", required = false) String firstName,
            @RequestParam(value = "last_name", required = false) String lastName) {

        List<Patient> patients = new LinkedList<>();
        patientRepository.findAll().forEach(patients::add);

        patients = new FilteringService<>(patients)
                .contains(firstName, Patient::getFirstName)
                .contains(lastName, Patient::getLastName)
                .getFiltered();

        return patients;
    }
}
