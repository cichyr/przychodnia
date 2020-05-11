package pl.clinic.visit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.clinic.doctor.model.Doctor;
import pl.clinic.doctor.model.DoctorRepository;
import pl.clinic.examination_category.model.ExaminationCategory;
import pl.clinic.examination_category.model.ExaminationCategoryRepository;
import pl.clinic.labolratory_examination.controller.dto.LaboratoryExaminationAdd;
import pl.clinic.labolratory_examination.model.ExaminationState;
import pl.clinic.labolratory_examination.model.ExaminationStateRepository;
import pl.clinic.labolratory_examination.model.LaboratoryExamination;
import pl.clinic.labolratory_examination.model.LaboratoryExaminationRepository;
import pl.clinic.patient.model.Patient;
import pl.clinic.patient.model.PatientRepository;
import pl.clinic.physical_examination.controller.dto.PhysicalExaminationAdd;
import pl.clinic.physical_examination.model.PhysicalExamination;
import pl.clinic.physical_examination.model.PhysicalExaminationRepository;
import pl.clinic.receptionist.model.Receptionist;
import pl.clinic.receptionist.model.ReceptionistRepository;
import pl.clinic.visit.controller.dto.Interview;
import pl.clinic.visit.controller.dto.VisitCreate;
import pl.clinic.visit.controller.dto.VisitDetails;
import pl.clinic.visit.model.Visit;
import pl.clinic.visit.model.VisitRepository;
import pl.clinic.visit.model.VisitState;
import pl.clinic.visit.model.VisitStateRepository;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/visits")
public class VisitController {

    @Autowired
    VisitRepository visitRepository;
    @Autowired
    VisitStateRepository visitStateRepository;
    @Autowired
    ReceptionistRepository receptionistRepository;
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    PhysicalExaminationRepository physicalExaminationRepository;
    @Autowired
    LaboratoryExaminationRepository laboratoryExaminationRepository;
    @Autowired
    ExaminationCategoryRepository examinationCategoryRepository;
    @Autowired
    ExaminationStateRepository examinationStateRepository;

    @GetMapping(value = "/{visit_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VisitDetails> getVisit(@PathVariable Long visit_id) {

        return visitRepository.findById(visit_id)
                .map(value -> ResponseEntity.ok(new VisitDetails(value)))
                .orElseGet(() -> ResponseEntity.ok().build());
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<VisitDetails>> getVisits() {

        Set<VisitDetails> visits = new HashSet<>();
        visitRepository.findAll().forEach(value -> visits.add(new VisitDetails(value)));
        return ResponseEntity.ok(visits);
    }

    //dodaj wizyte
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VisitDetails> postVisit(
            @RequestBody VisitCreate visit) {

        Optional<Receptionist> optionalReceptionist = receptionistRepository.findById(visit.getReceptionistId());
        Optional<Doctor> optionalDoctor = doctorRepository.findById(visit.getDoctorId());
        Optional<Patient> optionalPatient = patientRepository.findById(visit.getPatientId());

        if (!(optionalReceptionist.isPresent() && optionalDoctor.isPresent() && optionalPatient.isPresent())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Visit visitCreated = new Visit();
        visitCreated.setReceptionist(optionalReceptionist.get());
        visitCreated.setDoctor(optionalDoctor.get());
        visitCreated.setPatient(optionalPatient.get());
        visitCreated.setRegistrationDate(LocalDateTime.now());
        Optional<VisitState> state = visitStateRepository.findById((long) 1);
        visitCreated.setState(state.get());

        visitRepository.save(visitCreated);


        return ResponseEntity.ok(new VisitDetails(visitCreated));
    }

    //dodaj wywiad
    @PutMapping(value = "/{visit_id}/interview", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VisitDetails> putInterview(
            @PathVariable Long visit_id,
            @RequestBody Interview interview
    ) {
        Optional<Visit> optionalVisit = visitRepository.findById(visit_id);
        if (!optionalVisit.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        optionalVisit.get().setDescription(interview.getDescription());
        optionalVisit.get().setDiagnose(interview.getDiagnose());
        visitRepository.save(optionalVisit.get());

        return ResponseEntity.ok(new VisitDetails(optionalVisit.get()));
    }

    //dodaj badanie fizyczne
    @PostMapping(value = "/{visit_id}/physical_examinations", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VisitDetails> postPhysicalExamination(
            @PathVariable Long visit_id,
            @RequestBody PhysicalExaminationAdd physicalExaminationAdd
    ) {
        PhysicalExamination physicalExamination = new PhysicalExamination();
        physicalExamination.setResult(physicalExaminationAdd.getResult());
        Optional<ExaminationCategory> optionalExaminationCategory = examinationCategoryRepository.findById(physicalExaminationAdd.getCode());
        if (!optionalExaminationCategory.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        physicalExamination.setCategory(optionalExaminationCategory.get());

        Optional<Visit> optionalVisit = visitRepository.findById(visit_id);
        if (!optionalVisit.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        physicalExamination.setVisit(optionalVisit.get());
        physicalExaminationRepository.save(physicalExamination);
        return ResponseEntity.ok(new VisitDetails(optionalVisit.get()));
    }

    //dodaj badanie fizyczne
    @PostMapping(value = "/{visit_id}/laboratory_examinations", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VisitDetails> postLaboratoryExamination(
            @PathVariable Long visit_id,
            @RequestBody LaboratoryExaminationAdd laboratoryExaminationAdd
    ) {
        LaboratoryExamination laboratoryExamination = new LaboratoryExamination();
        laboratoryExamination.setDoctorNote(laboratoryExaminationAdd.getDoctorNotes());
        Optional<ExaminationCategory> optionalExaminationCategory = examinationCategoryRepository.findById(laboratoryExaminationAdd.getCode());
        if (!optionalExaminationCategory.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        laboratoryExamination.setCategory(optionalExaminationCategory.get());

        Optional<Visit> optionalVisit = visitRepository.findById(visit_id);
        if (!optionalVisit.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        laboratoryExamination.setVisit(optionalVisit.get());
        Optional<ExaminationState> state = examinationStateRepository.findById((long) 1);
        laboratoryExamination.setState(state.get());
        laboratoryExaminationRepository.save(laboratoryExamination);
        return ResponseEntity.ok(new VisitDetails(optionalVisit.get()));
    }

    //anuluj wizyte

    @PutMapping(value = "/{visit_id}/cancel", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VisitDetails> putCancel(
            @PathVariable Long visit_id
    ) {
        Optional<Visit> optionalVisit = visitRepository.findById(visit_id);
        if (!optionalVisit.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (optionalVisit.get().getState().getId() != 1) {
            return ResponseEntity.ok(new VisitDetails(optionalVisit.get()));
        }
        Optional<VisitState> state = visitStateRepository.findById((long) 3);
        optionalVisit.get().setState(state.get());
        optionalVisit.get().setFinalizationCancellationDate(LocalDateTime.now());
        visitRepository.save(optionalVisit.get());
        return ResponseEntity.ok(new VisitDetails(optionalVisit.get()));
    }


    //zakoncz wizyte
    @PutMapping(value = "/{visit_id}/finalize", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VisitDetails> putFinalize(
            @PathVariable Long visit_id
    ) {
        Optional<Visit> optionalVisit = visitRepository.findById(visit_id);
        if (!optionalVisit.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<VisitState> state = visitStateRepository.findById((long) 2);
        optionalVisit.get().setState(state.get());
        optionalVisit.get().setFinalizationCancellationDate(LocalDateTime.now());
        visitRepository.save(optionalVisit.get());
        return ResponseEntity.ok(new VisitDetails(optionalVisit.get()));
    }
}
