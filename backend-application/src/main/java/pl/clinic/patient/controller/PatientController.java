
package pl.clinic.patient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.clinic.common_services.FilteringService;
import pl.clinic.patient.controller.DTO.PatientAndDetailsDto;
import pl.clinic.patient.model.Patient;
import pl.clinic.patient.model.PatientRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    PatientRepository patientRepository;

    @GetMapping(value = "/{patient_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    PatientAndDetailsDto getPatient(@PathVariable Long patient_id) {
        Optional<Patient> patient = patientRepository.findById(patient_id);
        if (patient == null)
            return null;
        else
            return new PatientAndDetailsDto(patient.get());
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<Patient> getPatients(
            @RequestParam(value = "first_name", required = false) String firstName,
            @RequestParam(value = "last_name", required = false) String lastName) {
        long count = patientRepository.count();

        if (count == 0)
            return new ArrayList<>();

        Page<Patient> users = patientRepository.findAll(PageRequest.of(0, (int) count));
        List<Patient> patients = users.getContent();

        patients = new FilteringService<>(patients)
                .contains(firstName, Patient::getFirstName)
                .contains(lastName, Patient::getLastName)
                .getFiltered();

        return patients;
    }
}
