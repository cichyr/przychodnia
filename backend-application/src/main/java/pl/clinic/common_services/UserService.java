package pl.clinic.common_services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.clinic.app_user.model.AccountId;
import pl.clinic.app_user.model.Roles;
import pl.clinic.doctor.model.DoctorRepository;
import pl.clinic.lab_supervisor.model.LabSupervisorRepository;
import pl.clinic.lab_worker.model.LabWorkerRepository;
import pl.clinic.receptionist.model.ReceptionistRepository;
import pl.clinic.user.model.User;

import java.util.Optional;

/**
 * Provides functionality which allows to associate an Account with a User.
 */
@Service
public class UserService {

    @Autowired
    private ReceptionistRepository receptionistRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private LabWorkerRepository labWorkerRepository;

    @Autowired
    private LabSupervisorRepository labSupervisorRepository;

    public Optional<? extends User> findByAccountId(AccountId id) {
        switch (id.getRole().getName()) {
            case Roles.DOCTOR:
                return doctorRepository.findById(id.getEmployeeId());
            case Roles.RECEPTIONIST:
                return receptionistRepository.findById(id.getEmployeeId());
            case Roles.LAB_WORKER:
                return labWorkerRepository.findById(id.getEmployeeId());
            case Roles.LAB_SUPERVISOR:
                return labSupervisorRepository.findById(id.getEmployeeId());
            default:
                return Optional.empty();
        }
    }
}
