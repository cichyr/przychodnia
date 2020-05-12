package pl.clinic.physical_examination.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.clinic.examination_category.controller.dto.ExaminationBasic;
import pl.clinic.physical_examination.controller.dto.PhysicalExaminationDetails;
import pl.clinic.physical_examination.model.PhysicalExaminationRepository;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/physical_examinations")
public class PhysicalExaminationController {
    @Autowired
    PhysicalExaminationRepository physicalExaminationRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ExaminationBasic>> getPhysicalExaminations() {

        List<ExaminationBasic> examinations = new LinkedList<>();
        physicalExaminationRepository.findAll().forEach(value -> examinations.add(new ExaminationBasic(value)));
        return ResponseEntity.ok(examinations);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PhysicalExaminationDetails> getPhysicalExamination(@PathVariable Long id) {
        return physicalExaminationRepository.findById(id)
                .map(value -> ResponseEntity.ok(new PhysicalExaminationDetails(value)))
                .orElseGet(() -> ResponseEntity.ok().build());
    }

}
