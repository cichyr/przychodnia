package pl.clinic.doctor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.clinic.common_services.FilteringService;
import pl.clinic.doctor.model.Doctor;
import pl.clinic.doctor.model.DoctorRepository;
import pl.clinic.visit.controller.dto.BasicVisit;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    DoctorRepository doctorRepository;

    @GetMapping(value = "/{doctor_id}/visits", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<BasicVisit>> getDoctorsVisits(@PathVariable Long doctor_id) {

        Set<BasicVisit> visits = new HashSet<>();

        doctorRepository.findById(doctor_id).get().getVisits()
                .forEach(value->visits.add(new BasicVisit(value)));

        return ResponseEntity.ok(visits);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Doctor>> getDoctors(
            @RequestParam(value = "first_name", required = false) String firstName,
            @RequestParam(value = "last_name", required = false) String lastName) {

        List<Doctor> doctors = new LinkedList<>();
        doctorRepository.findAllWhereAccountEnabled().forEach(doctors::add);

        doctors = new FilteringService<>(doctors)
                .contains(firstName, Doctor::getFirstName)
                .contains(lastName, Doctor::getLastName)
                .getFiltered();

        return ResponseEntity.ok(doctors);
    }
}
