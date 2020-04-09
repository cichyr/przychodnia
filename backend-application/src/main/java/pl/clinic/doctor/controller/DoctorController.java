package pl.clinic.doctor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.clinic.doctor.controller.DTO.VisitsDto;
import pl.clinic.doctor.model.Doctor;
import pl.clinic.doctor.model.DoctorRepository;
import pl.clinic.visit.model.Visit;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    DoctorRepository doctorRepository;

    @GetMapping(value = "/{doctor_id}/visits", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Set<VisitsDto> getDoctorsVisits(@PathVariable Long doctor_id) {
        Optional<Doctor> doctor = doctorRepository.findById(doctor_id);

        if (!doctor.isPresent())
            return null;

        Set<Visit> visits = doctor.get().getVisits();
        Set<VisitsDto> visitsDto = new HashSet<>();

        for (Visit vi : visits)
            visitsDto.add(new VisitsDto(vi));

        return visitsDto;
    }
}
