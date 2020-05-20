package pl.clinic.labolratory_examination.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import pl.clinic.account.controller.AccountController;
import pl.clinic.account.model.AccountDetails;
import pl.clinic.account.model.AccountId;
import pl.clinic.common_services.UserService;
import pl.clinic.lab_supervisor.model.LabSupervisor;
import pl.clinic.lab_supervisor.model.LabSupervisorRepository;
import pl.clinic.lab_worker.model.LabWorker;
import pl.clinic.lab_worker.model.LabWorkerRepository;
import pl.clinic.labolratory_examination.controller.dto.LaboratoryExaminationBasic;
import pl.clinic.labolratory_examination.controller.dto.LaboratoryExaminationDetails;
import pl.clinic.labolratory_examination.controller.dto.LaboratoryExaminationResult;
import pl.clinic.labolratory_examination.model.ExaminationState;
import pl.clinic.labolratory_examination.model.ExaminationStateRepository;
import pl.clinic.labolratory_examination.model.LaboratoryExamination;
import pl.clinic.labolratory_examination.model.LaboratoryExaminationRepository;
import pl.clinic.user.model.User;
import pl.clinic.visit.controller.dto.Interview;
import pl.clinic.visit.controller.dto.VisitDetails;
import pl.clinic.visit.model.Visit;
import pl.clinic.visit.model.VisitState;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/laboratory_examinations")
public class LaboratoryExaminationController {
    @Autowired
    LaboratoryExaminationRepository laboratoryExaminationRepository;
    @Autowired
    LabWorkerRepository labWorkerRepository;
    @Autowired
    LabSupervisorRepository labSupervisorRepository;
    @Autowired
    ExaminationStateRepository examinationStateRepository;

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


    @PutMapping(value = "/{id}/result", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LaboratoryExaminationDetails> putLaboratoryExaminationResult(
            @PathVariable Long id,
            @RequestBody LaboratoryExaminationResult result
    ) {
        //znalezienie badania
        Optional<LaboratoryExamination> optionalLaboratoryExamination = laboratoryExaminationRepository.findById(id);
        if (!optionalLaboratoryExamination.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        LaboratoryExamination examination = optionalLaboratoryExamination.get();
        //czy własciwy stan(zlecone)
        if (examination.getState().getId() == 1) {
            examination.setResult(result.getResult());
            laboratoryExaminationRepository.save(examination);
        }
        return ResponseEntity.ok(new LaboratoryExaminationDetails(examination));
    }

    @PutMapping(value = "/{id}/cancel_laborant", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LaboratoryExaminationDetails> putCancelLaborant(Principal principal,
                                                                          @PathVariable Long id
    ) {
        //pobranie użytkownika
        AccountDetails accountDetails;
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) principal;
        Optional<AccountDetails> optionalAccountDetails = Optional.of((AccountDetails) token.getPrincipal());
        accountDetails = optionalAccountDetails.get();
        if(!accountDetails.getRole().getName().equals("LABW"))
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "Must be LabWorker to proceed");
        //znalezienie badania
        Optional<LaboratoryExamination> optionalLaboratoryExamination = laboratoryExaminationRepository.findById(id);
        if (!optionalLaboratoryExamination.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        LaboratoryExamination examination = optionalLaboratoryExamination.get();
        //sprawdzenie stanu
        if (examination.getState().getId() == 1 && !examination.getResult().isEmpty()) {
            //znalezienie laboranta
            Optional<LabWorker> optionalLabWorker = labWorkerRepository.findById(accountDetails.getId());
            if (!optionalLabWorker.isPresent()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            examination.setLabWorker(optionalLabWorker.get());

            examination.setExecutionCancellationDate(LocalDateTime.now());
            Optional<ExaminationState> state = examinationStateRepository.findById((long) 3);
            examination.setState(state.get());
            laboratoryExaminationRepository.save(examination);
        }
        return ResponseEntity.ok(new LaboratoryExaminationDetails(examination));
    }

    @PutMapping(value = "/{id}/approve_laborant", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LaboratoryExaminationDetails> putApproveLaboratorant(
            Principal principal, @PathVariable Long id
    ) {
        //pobranie użytkownika
        AccountDetails accountDetails;
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) principal;
        Optional<AccountDetails> optionalAccountDetails = Optional.of((AccountDetails) token.getPrincipal());
        accountDetails = optionalAccountDetails.get();
        if(!accountDetails.getRole().getName().equals("LABW") && !accountDetails.getRole().getName().equals("LABS"))
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "Must be LabWorker to proceed");
        //znalezienie badania
        Optional<LaboratoryExamination> optionalLaboratoryExamination = laboratoryExaminationRepository.findById(id);
        if (!optionalLaboratoryExamination.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        LaboratoryExamination examination = optionalLaboratoryExamination.get();
        //sprawdzenie stanu
        if (examination.getState().getId() == 1 && !examination.getResult().isEmpty()) {
            //znalezienie laboranta
            Optional<LabWorker> optionalLabWorker = labWorkerRepository.findById(accountDetails.getId());
            if (!optionalLabWorker.isPresent()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            examination.setLabWorker(optionalLabWorker.get());

            examination.setExecutionCancellationDate(LocalDateTime.now());
            Optional<ExaminationState> state = examinationStateRepository.findById((long) 2);
            examination.setState(state.get());
            laboratoryExaminationRepository.save(examination);
        }
        return ResponseEntity.ok(new LaboratoryExaminationDetails(examination));
    }

    @PutMapping(value = "/{id}/supervisor_note", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LaboratoryExaminationDetails> putSupervisorNote(
            @PathVariable Long id, @RequestBody LaboratoryExaminationResult result
    ) {
        //znalezienie badania
        Optional<LaboratoryExamination> optionalLaboratoryExamination = laboratoryExaminationRepository.findById(id);
        if (!optionalLaboratoryExamination.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        LaboratoryExamination examination = optionalLaboratoryExamination.get();
        //czy własciwy stan(wykonane)
        if (examination.getState().getId() == 2 || examination.getState().getId() == 3) {
            examination.setSupervisorNote(result.getResult());
            laboratoryExaminationRepository.save(examination);
        }
        return ResponseEntity.ok(new LaboratoryExaminationDetails(examination));
    }

    @PutMapping(value = "/{id}/cancel_supervisor", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LaboratoryExaminationDetails> putCancelSupervisor(
            Principal principal, @PathVariable Long id
    ) {
        //pobranie użytkownika
        AccountDetails accountDetails;
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) principal;
        Optional<AccountDetails> optionalAccountDetails = Optional.of((AccountDetails) token.getPrincipal());
        accountDetails = optionalAccountDetails.get();
        if(!accountDetails.getRole().getName().equals("LABS"))
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "Must be LabSupervisor to proceed");
        //znalezienie badania
        Optional<LaboratoryExamination> optionalLaboratoryExamination = laboratoryExaminationRepository.findById(id);
        if (!optionalLaboratoryExamination.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        LaboratoryExamination examination = optionalLaboratoryExamination.get();
        //sprawdzenie stanu
        if ((examination.getState().getId() == 2 || examination.getState().getId() == 3) && !examination.getSupervisorNote().isEmpty()) {
            //znalezienie kierownika laboratorium
            Optional<LabSupervisor> optionalLabSupervisor = labSupervisorRepository.findById(accountDetails.getId());
            if (!optionalLabSupervisor.isPresent()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            examination.setLabSupervisor(optionalLabSupervisor.get());

            examination.setApprovalCancellationDate(LocalDateTime.now());
            Optional<ExaminationState> state = examinationStateRepository.findById((long) 3);
            examination.setState(state.get());
            laboratoryExaminationRepository.save(examination);
        }
        return ResponseEntity.ok(new LaboratoryExaminationDetails(examination));
    }

    @PutMapping(value = "/{id}/approve_supervisor", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LaboratoryExaminationDetails> putApproveSupervisor(
            Principal principal, @PathVariable Long id
    ) {
        //pobranie użytkownika
        AccountDetails accountDetails;
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) principal;
        Optional<AccountDetails> optionalAccountDetails = Optional.of((AccountDetails) token.getPrincipal());
        accountDetails = optionalAccountDetails.get();

        if(!accountDetails.getRole().getName().equals("LABS"))
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "Must be LabSupervisor to proceed");
        //znalezienie badania
        Optional<LaboratoryExamination> optionalLaboratoryExamination = laboratoryExaminationRepository.findById(id);
        if (!optionalLaboratoryExamination.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        LaboratoryExamination examination = optionalLaboratoryExamination.get();
        //sprawdzenie stanu
        if ((examination.getState().getId() == 2 || examination.getState().getId() == 3) && !examination.getSupervisorNote().isEmpty()) {
            //znalezienie kierownika laboratorium
            Optional<LabSupervisor> optionalLabSupervisor = labSupervisorRepository.findById(accountDetails.getId());
            if (!optionalLabSupervisor.isPresent()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            examination.setLabSupervisor(optionalLabSupervisor.get());

            examination.setApprovalCancellationDate(LocalDateTime.now());
            Optional<ExaminationState> state = examinationStateRepository.findById((long) 4);
            examination.setState(state.get());
            laboratoryExaminationRepository.save(examination);
        }
        return ResponseEntity.ok(new LaboratoryExaminationDetails(examination));
    }
}
