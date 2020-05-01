package pl.clinic.receptionist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.clinic.receptionist.model.ReceptionistRepository;
import pl.clinic.visit.controller.dto.BasicVisit;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/receptionists")
public class ReceptionistController {

    @Autowired
    ReceptionistRepository receptionistRepository;

    @GetMapping(value = "/{receptionist_id}/visits", produces = MediaType.APPLICATION_JSON_VALUE)
    public
    ResponseEntity<Set<BasicVisit>> getReceptionistsVisits(@PathVariable Long receptionist_id) {
        Set<BasicVisit> visits = new HashSet<>();

        receptionistRepository.findById(receptionist_id).get().getVisits()
                .forEach(value->visits.add(new BasicVisit(value)));

        return ResponseEntity.ok(visits);
    }
}
