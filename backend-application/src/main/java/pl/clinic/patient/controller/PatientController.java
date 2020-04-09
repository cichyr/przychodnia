
package pl.clinic.patient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.clinic.patient.model.Patient;
import pl.clinic.patient.model.PatientRepository;

import java.util.Optional;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    PatientRepository patientRepository;

    @GetMapping(value = "/{patient_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Patient getPatient(@PathVariable Long patient_id) {
        Optional<Patient> user = patientRepository.findById(patient_id);
        return user.orElse(null);
    }
}
