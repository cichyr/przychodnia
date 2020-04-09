package pl.clinic.doctor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.clinic.common_services.FilteringService;
import pl.clinic.doctor.controller.DTO.VisitsDto;
import pl.clinic.doctor.model.Doctor;
import pl.clinic.doctor.model.DoctorRepository;
import pl.clinic.visit.model.Visit;

import java.util.HashSet;
import java.util.List;
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

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<Doctor> getDoctors(
            @RequestParam(value = "first_name", required = false) String firstName,
            @RequestParam(value = "last_name", required = false) String lastName) {

        long count = doctorRepository.count();
        if (count == 0)
            return null;

        Page<Doctor> doctors = doctorRepository.findAll(PageRequest.of(0, (int) count));
        List<Doctor> filteredDoctors = doctors.getContent();

        filteredDoctors = new FilteringService<>(filteredDoctors)
                .contains(firstName, Doctor::getFirstName)
                .contains(lastName, Doctor::getLastName)
                .getFiltered();

        return filteredDoctors;
    }
}
