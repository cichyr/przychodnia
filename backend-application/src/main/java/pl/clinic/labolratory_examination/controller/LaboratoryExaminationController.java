package pl.clinic.labolratory_examination.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.clinic.examination_category.controller.ExaminationBasic;
import pl.clinic.labolratory_examination.controller.dto.LaboratoryExaminationBasic;
import pl.clinic.labolratory_examination.controller.dto.LaboratoryExaminationDetails;
import pl.clinic.labolratory_examination.model.LaboratoryExaminationRepository;
import pl.clinic.physical_examination.controller.dto.PhysicalExaminationDetails;
import pl.clinic.physical_examination.model.PhysicalExaminationRepository;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/laboratory_examinations")
public class LaboratoryExaminationController {
    @Autowired
    LaboratoryExaminationRepository laboratoryExaminationRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LaboratoryExaminationBasic>> getPhysicalExaminations() {

        List<LaboratoryExaminationBasic> examinations = new LinkedList<>();
        laboratoryExaminationRepository.findAll().forEach(value -> examinations.add(new LaboratoryExaminationBasic(value)));
        return ResponseEntity.ok(examinations);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LaboratoryExaminationDetails> getPhysicalExamination(@PathVariable Long id) {
        return laboratoryExaminationRepository.findById(id)
                .map(value -> ResponseEntity.ok(new LaboratoryExaminationDetails(value)))
                .orElseGet(() -> ResponseEntity.ok().build());
    }

}
