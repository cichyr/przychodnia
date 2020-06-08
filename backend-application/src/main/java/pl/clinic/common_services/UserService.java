package pl.clinic.common_services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.clinic.account.model.AccountId;
import pl.clinic.account.model.Role;
import pl.clinic.account.model.RoleRepository;
import pl.clinic.account.model.Roles;
import pl.clinic.admin.Admin;
import pl.clinic.admin.AdminRepository;
import pl.clinic.doctor.model.Doctor;
import pl.clinic.doctor.model.DoctorRepository;
import pl.clinic.lab_supervisor.model.LabSupervisor;
import pl.clinic.lab_supervisor.model.LabSupervisorRepository;
import pl.clinic.lab_worker.model.LabWorker;
import pl.clinic.lab_worker.model.LabWorkerRepository;
import pl.clinic.receptionist.model.Receptionist;
import pl.clinic.receptionist.model.ReceptionistRepository;
import pl.clinic.user.model.User;

import java.util.Optional;

/**
 * Provides functionality which allows to associate an Account with a User.
 */
@Service
public class UserService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ReceptionistRepository receptionistRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private LabWorkerRepository labWorkerRepository;

    @Autowired
    private LabSupervisorRepository labSupervisorRepository;

    @Autowired
    private AdminRepository adminRepository;

    public Optional<? extends User> findByAccountId(AccountId id) {
        Optional<Role> role = roleRepository.findById(id.getRoleId());
        if(!role.isPresent()) return Optional.empty();
        switch (role.get().getName()) {
            case Roles.DOCTOR:
                return doctorRepository.findById(id.getEmployeeId());
            case Roles.RECEPTIONIST:
                return receptionistRepository.findById(id.getEmployeeId());
            case Roles.LAB_WORKER:
                return labWorkerRepository.findById(id.getEmployeeId());
            case Roles.LAB_SUPERVISOR:
                return labSupervisorRepository.findById(id.getEmployeeId());
            case Roles.ADMINISTRATOR:
                return adminRepository.findById(id.getEmployeeId());
            default:
                return Optional.empty();
        }
    }

    public void save(User user, Role role) {
        switch (role.getName()) {
            case Roles.DOCTOR:
                assert user instanceof Doctor;
                doctorRepository.save((Doctor) user);
                break;
            case Roles.RECEPTIONIST:
                assert user instanceof Receptionist;
                receptionistRepository.save((Receptionist) user);
                break;
            case Roles.LAB_WORKER:
                assert user instanceof LabWorker;
                labWorkerRepository.save((LabWorker) user);
                break;
            case Roles.LAB_SUPERVISOR:
                assert user instanceof LabSupervisor;
                labSupervisorRepository.save((LabSupervisor) user);
                break;
            case Roles.ADMINISTRATOR:
                assert user instanceof Admin;
                adminRepository.save((Admin) user);
                break;
        }
    }
}
