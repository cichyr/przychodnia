package pl.clinic.receptionist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.clinic.receptionist.controller.DTO.VisitDto;
import pl.clinic.receptionist.model.Receptionist;
import pl.clinic.receptionist.model.ReceptionistRepository;
import pl.clinic.visit.model.Visit;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/receptionists")
public class ReceptionistController {

    @Autowired
    ReceptionistRepository receptionistRepository;

    @GetMapping(value = "/{receptionist_id}/visits", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Set<VisitDto> getReceptionistsVisits(@PathVariable Long receptionist_id) {
        Optional<Receptionist> receptionist = receptionistRepository.findById(receptionist_id);

        if (!receptionist.isPresent())
            return null;

        Set<Visit> visits = receptionist.get().getVisits();
        Set<VisitDto> visitsDto = new HashSet<>();

        for (Visit vi : visits)
            visitsDto.add(new VisitDto(vi));

        return visitsDto;
    }
}
