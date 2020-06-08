package pl.clinic.doctor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.clinic.common_services.FilteringService;
import pl.clinic.doctor.model.Doctor;
import pl.clinic.doctor.model.DoctorRepository;
import pl.clinic.visit.controller.dto.BasicVisit;
import pl.clinic.visit.model.Visit;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    DoctorRepository doctorRepository;

    @GetMapping(value = "/{doctor_id}/visits", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<BasicVisit>> getDoctorsVisits(@PathVariable Long doctor_id,
            @RequestParam(value = "date", required = false) String date) {

        Set<Visit> visits = new HashSet<Visit>();

        visits = doctorRepository.findById(doctor_id).get().getVisits();

        Set<BasicVisit> filtered = new HashSet<>();
        if(date!=null){
            LocalDate date1 = LocalDate.parse(date);
            for(Visit v:visits){
                if(v.getAppointmentDateTime()!=null&&v.getAppointmentDateTime().toLocalDate().equals(date1)){
                    filtered.add(new BasicVisit(v));
                }
            }
        }else {
            for (Visit v : visits) {
                    filtered.add(new BasicVisit(v));
            }
        }

        return ResponseEntity.ok(filtered);
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
