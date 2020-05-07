package pl.clinic.examination_category.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.clinic.common_services.FilteringService;
import pl.clinic.examination_category.model.ExaminationCategoryRepository;
import pl.clinic.labolratory_examination.model.LaboratoryExamination;
import pl.clinic.labolratory_examination.model.LaboratoryExaminationRepository;
import pl.clinic.patient.model.Patient;
import pl.clinic.physical_examination.model.PhysicalExamination;
import pl.clinic.physical_examination.model.PhysicalExaminationRepository;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;




@RestController
@RequestMapping("/possible_examinations")
public class PossibleExaminationsController {
    @Autowired
    ExaminationCategoryRepository examinationCategoryRepository;
    @Autowired
    LaboratoryExaminationRepository laboratoryExaminationRepository;
    @Autowired
    PhysicalExaminationRepository physicalExaminationRepository;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ExaminationBasic>> getPossibleExaminations(
            @RequestParam(value = "examination_type", required = false) String examination_type) {
        List<ExaminationBasic> examinations = new ArrayList<ExaminationBasic>();
            if(examination_type!=null){
                if(examination_type.equals("laboratory")){
                    laboratoryExaminationRepository.findAll().forEach(value->examinations.add(new ExaminationBasic(value)));
                    return ResponseEntity.ok(examinations);
                }else if(examination_type.equals("physical")) {
                    physicalExaminationRepository.findAll().forEach(value -> examinations.add(new ExaminationBasic(value)));
                    return ResponseEntity.ok(examinations);
                }
            }
                laboratoryExaminationRepository.findAll().forEach(value->examinations.add(new ExaminationBasic(value)));
                physicalExaminationRepository.findAll().forEach(value->examinations.add(new ExaminationBasic(value)));
                return ResponseEntity.ok(examinations);


    }

}
