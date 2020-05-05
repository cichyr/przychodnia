package pl.clinic.visit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.clinic.doctor.model.Doctor;
import pl.clinic.doctor.model.DoctorRepository;
import pl.clinic.patient.model.Patient;
import pl.clinic.patient.model.PatientRepository;
import pl.clinic.receptionist.model.Receptionist;
import pl.clinic.receptionist.model.ReceptionistRepository;
import pl.clinic.visit.controller.dto.VisitDetails;
import pl.clinic.visit.model.Visit;
import pl.clinic.visit.model.VisitRepository;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/visits")
public class VisitController {

    @Autowired
    VisitRepository visitRepository;
    ReceptionistRepository receptionistRepository;
    DoctorRepository doctorRepository;
    PatientRepository patientRepository;

    @GetMapping(value = "/{visit_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VisitDetails> getVisit(@PathVariable Long visit_id) {

        return visitRepository.findById(visit_id)
                .map(value -> ResponseEntity.ok(new VisitDetails(value)))
                .orElseGet(() -> ResponseEntity.ok().build());


    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<VisitDetails>> getVisits(){

        Set<VisitDetails> visits = new HashSet<>();

        visitRepository.findAll()
                .forEach(value->visits.add(new VisitDetails(value)));

        return ResponseEntity.ok(visits);
    }

    //dodaj wizyte
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VisitDetails> postVisit(
            @RequestParam(value = "receptionistId") Long receptionistId,
            @RequestParam(value = "doctorId") Long doctorId,
            @RequestParam(value = "patientId") Long patientId
    ) {
        Optional<Receptionist> optionalReceptionist = receptionistRepository.findById(receptionistId);
        Optional<Doctor> optionalDoctor = doctorRepository.findById(doctorId);
        Optional<Patient> optionalPatient = patientRepository.findById(patientId);

        if (! (optionalReceptionist.isPresent() && optionalPatient.isPresent() && optionalPatient.isPresent())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Visit visit = new Visit();
        visit.setReceptionist(optionalReceptionist);
        visit.setDoctor(optionalDoctor);
        visit.setPatient(optionalPatient);
        visit.setRegistrationDate(LocalDateTime.now());

        return ResponseEntity.ok(new VisitDetails(visit));


    }
}