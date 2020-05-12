package pl.clinic.examination_category.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.clinic.examination_category.controller.dto.ExaminationBasic;
import pl.clinic.examination_category.model.ExaminationCategory;
import pl.clinic.examination_category.model.ExaminationCategoryRepository;
import pl.clinic.examination_category.model.ExaminationType;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/possible_examinations")
public class PossibleExaminationsController {
    @Autowired
    ExaminationCategoryRepository examinationCategoryRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ExaminationBasic>> getPossibleExaminations(
            @RequestParam(value = "examination_type", required = false) String examination_type) {
        List<ExaminationBasic> examinationsList = new ArrayList<ExaminationBasic>();
        if (examination_type != null) {
            if (examination_type.equals("laboratory")) {
                examination_type = ExaminationType.L.toString();
                for (ExaminationCategory value : examinationCategoryRepository.findAll()) {
                    if (value.getType().toString().equals(examination_type)) {
                        examinationsList.add(new ExaminationBasic((value)));
                    }
                }
                return ResponseEntity.ok(examinationsList);
            } else if (examination_type.equals("physical")) {
                examination_type = ExaminationType.P.toString();
                for (ExaminationCategory value : examinationCategoryRepository.findAll()) {
                    if (value.getType().toString().equals(examination_type)) {
                        examinationsList.add(new ExaminationBasic((value)));
                    }
                }
                return ResponseEntity.ok(examinationsList);
            }
        }
        for (ExaminationCategory value : examinationCategoryRepository.findAll()) {
            examinationsList.add(new ExaminationBasic((value)));
        }
        return ResponseEntity.ok(examinationsList);
    }

}
