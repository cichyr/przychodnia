package pl.clinic.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;
import pl.clinic.account.controller.dto.AccountBasicsDto;
import pl.clinic.account.controller.dto.AccountDetailsDto;
import pl.clinic.account.controller.dto.AccountNewDto;
import pl.clinic.account.controller.dto.NewPasswordDto;
import pl.clinic.account.model.*;
import pl.clinic.admin.Admin;
import pl.clinic.admin.AdminRepository;
import pl.clinic.common_services.FilteringService;
import pl.clinic.common_services.UserService;
import pl.clinic.doctor.model.Doctor;
import pl.clinic.doctor.model.DoctorRepository;
import pl.clinic.lab_supervisor.model.LabSupervisor;
import pl.clinic.lab_supervisor.model.LabSupervisorRepository;
import pl.clinic.lab_worker.model.LabWorker;
import pl.clinic.lab_worker.model.LabWorkerRepository;
import pl.clinic.receptionist.model.Receptionist;
import pl.clinic.receptionist.model.ReceptionistRepository;
import pl.clinic.user.model.PersonDetails;
import pl.clinic.user.model.PersonDetailsRepository;
import pl.clinic.user.model.User;

import javax.validation.Valid;
import java.security.Principal;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class AccountController {

    @Autowired
    private UserService userService;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    ReceptionistRepository receptionistRepository;

    @Autowired
    LabWorkerRepository labWorkerRepository;

    @Autowired
    LabSupervisorRepository labSupervisorRepository;

    @Autowired
    PersonDetailsRepository personDetailsRepository;

    @Autowired
    AdminRepository adminRepository;

    @GetMapping(value = "/userinfo", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AccountDetailsDto> getUserInfo(Principal principal) {
        AccountDetails accountDetails = resolveAccountDetails(principal)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not authenticated"));

        User user = userService
                .findByAccountId(new AccountId(accountDetails.getId(), accountDetails.getRole().getId()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User entity not found"));

        return ResponseEntity.ok(buildResponseAccountDetailsDto(accountDetails, user));
    }

    private Optional<AccountDetails> resolveAccountDetails(Principal principal) {

        if (principal == null) return Optional.empty();

        if (!(principal instanceof UsernamePasswordAuthenticationToken)) return Optional.empty();

        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) principal;

        if (token.getPrincipal() == null) return Optional.empty();
        if (!(token.getPrincipal() instanceof AccountDetails)) return Optional.empty();

        return Optional.of((AccountDetails) token.getPrincipal());

    }

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AccountBasicsDto>> getUsers(
            @RequestParam(value = "id", required = false) Long userId,
            @RequestParam(value = "username", required = false) String userName,
            @RequestParam(value = "first_name", required = false) String firstName,
            @RequestParam(value = "last_name", required = false) String lastName) {

        final List<AccountBasicsDto> accountBasicsDtos = new LinkedList<>();
        List<Role> roles = StreamSupport.stream(roleRepository.findAll().spliterator(), false).collect(Collectors.toList());

        accountRepository.findAll().forEach(account -> {
            Role role = roles.stream()
                    .filter(r -> r.getId().equals(account.getRoleId()))
                    .findAny()
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role id not found."));

            accountBasicsDtos.add(new AccountBasicsDto(new AccountDetails.Builder()
                    .account(account)
                    .role(role)
                    .build()));
        });


        Optional<? extends User> user;
        for (AccountBasicsDto account : accountBasicsDtos) {
            user = userService.findByAccountId(new AccountId(account.getId(), account.getRole().getId()));

            if (user.isPresent()) {
                account.setFirstName(user.get().getFirstName());
                account.setLastName(user.get().getLastName());
            }
        }


        return ResponseEntity.ok(
                new FilteringService<>(accountBasicsDtos)
                .contains(userId, AccountBasicsDto::getId)
                .contains(firstName, AccountBasicsDto::getFirstName)
                .contains(lastName, AccountBasicsDto::getLastName)
                .contains(userName, AccountBasicsDto::getUsername)
                .getFiltered()
        );
    }

    @GetMapping(value = "/user_details", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountDetailsDto> getAccountDetails(
            @RequestParam(value = "employee_id") Long accountId,
            @RequestParam(value = "role_id") Long roleId) {

        Optional<Role> role = roleRepository.findById(roleId);

        if (!role.isPresent()) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Role not exist.");
        }

        Optional<? extends User> optionalUser = userService.findByAccountId(new AccountId(accountId, role.get().getId()));

        if (!optionalUser.isPresent()) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Account not exist.");
        }

        Optional<Account> account = accountRepository.findById(new AccountId(accountId, role.get().getId()));

        if (!account.isPresent())
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "User not exist.");

        AccountDetails accountDetails = new AccountDetails.Builder()
                .account(account.get())
                .role(role.get())
                .build();

        return ResponseEntity.ok(buildResponseAccountDetailsDto(accountDetails, optionalUser.get()));
    }

    @PutMapping(value = "/user_details", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountDetailsDto> updateUser(
            @RequestParam(value = "employee_id") Long employee_id,
            @RequestParam(value = "role_id") Long roleId,
            @Valid @RequestBody AccountDetailsDto updatedAccount) {

        Role role = roleRepository
                .findById(roleId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The provided role does not exist."));

        Account account = accountRepository
                .findById(new AccountId(employee_id, role.getId()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no account for the provided employee_id and role."));

        User user = userService
                .findByAccountId(new AccountId(employee_id, role.getId()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No user with the given id exists."));

        // update user
        user.setFirstName(updatedAccount.getFirstName());
        user.setLastName(updatedAccount.getLastName());
        user.setLicenseCode(updatedAccount.getLicenseCode());

        //update personal details
        user.getPersonDetails().setCity(updatedAccount.getCity());
        user.getPersonDetails().setStreetAddress1(updatedAccount.getStreetAddress1());
        user.getPersonDetails().setStreetAddress2(updatedAccount.getStreetAddress2());
        user.getPersonDetails().setZipCode(updatedAccount.getZipCode());
        user.getPersonDetails().setRegion(updatedAccount.getRegion());
        user.getPersonDetails().setContactNumber(updatedAccount.getContactNumber());

        userService.save(user, role);

        // update account status
        account.setStatus(AccountStatus.ENABLED.name().equals(updatedAccount.getStatus()) ? AccountStatus.ENABLED : AccountStatus.DISABLED);
        accountRepository.save(account);

        AccountDetails accountDetails = new AccountDetails.Builder()
                .account(account)
                .role(role)
                .build();

        AccountDetailsDto accountDetailsDto = buildResponseAccountDetailsDto(accountDetails, user);

        return ResponseEntity.ok(accountDetailsDto);
    }

    @PostMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountDetailsDto> addUser(
            @Valid @RequestBody AccountNewDto newAccount) {

        Account account = accountRepository
                .findAppUserByUsername(newAccount.getUsername());
        if(account!=null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already exists");
        }
        account = new Account();
        PersonDetails details = new PersonDetails();


        details.setCity(newAccount.getCity());
        details.setContactNumber(newAccount.getContactNumber());
        details.setRegion(newAccount.getRegion());
        details.setStreetAddress1(newAccount.getStreetAddress1());
        details.setStreetAddress2(newAccount.getStreetAddress2());
        details.setZipCode(newAccount.getZipCode());
        details = personDetailsRepository.save(details);
       //konto pracownika
        Doctor doctor = new Doctor();
        Receptionist receptionist = new Receptionist();
        LabSupervisor labSupervisor = new LabSupervisor();
        LabWorker labWorker = new LabWorker();
        Admin admin = new Admin();
        switch(newAccount.getRole()){
            case "DOC":
                doctor.setLicenseCode(newAccount.getLicenseCode());
                doctor.setFirstName(newAccount.getFirstName());
                doctor.setLastName(newAccount.getLastName());
                doctor.setPersonDetails(details);
                doctor= doctorRepository.save(doctor);
                account.setEmployeeId(doctor.getId());
                account.setRoleId((long) 1);
            break;
            case "REC":
                receptionist.setLicenseCode(newAccount.getLicenseCode());
                receptionist.setFirstName(newAccount.getFirstName());
                receptionist.setLastName(newAccount.getLastName());
                receptionist.setPersonDetails(details);
                receptionist = receptionistRepository.save(receptionist);
                account.setEmployeeId(receptionist.getId());
                account.setRoleId((long) 2);
                break;
            case "LABS":
                labSupervisor.setLicenseCode(newAccount.getLicenseCode());
                labSupervisor.setFirstName(newAccount.getFirstName());
                labSupervisor.setLastName(newAccount.getLastName());
                labSupervisor.setPersonDetails(details);
                labSupervisor = labSupervisorRepository.save(labSupervisor);
                account.setEmployeeId(labSupervisor.getId());
                account.setRoleId((long) 3);
                break;
            case "LABW":
                labWorker.setLicenseCode(newAccount.getLicenseCode());
                labWorker.setFirstName(newAccount.getFirstName());
                labWorker.setLastName(newAccount.getLastName());
                labWorker.setPersonDetails(details);
                labWorker = labWorkerRepository.save(labWorker);
                account.setEmployeeId(labWorker.getId());
                account.setRoleId((long) 4);
                break;
            case "ADMIN":
                admin.setLicenseCode(newAccount.getLicenseCode());
                admin.setFirstName(newAccount.getFirstName());
                admin.setLastName(newAccount.getLastName());
                admin.setPersonDetails(details);
                admin = adminRepository.save(admin);
                account.setEmployeeId(admin.getId());
                account.setRoleId((long) 5);
                break;
        }

        account.setUsername(newAccount.getUsername());
        account.setHash(newAccount.getPassword());
        account.setStatus(AccountStatus.ENABLED.name().equals(newAccount.getStatus()) ? AccountStatus.ENABLED : AccountStatus.DISABLED);
        account = accountRepository.save(account);



        Optional<Role> role = roleRepository.findById(account.getRoleId());

        if (!role.isPresent()) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Role not exist.");
        }

        Optional<? extends User> optionalUser = userService.findByAccountId(new AccountId(account.getEmployeeId(), role.get().getId()));

        if (!optionalUser.isPresent()) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Account not exist.");
        }

        Optional<Account> accountOptional = accountRepository.findById(new AccountId(account.getEmployeeId(), role.get().getId()));

        if (!accountOptional.isPresent())
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "User not exist.");

        AccountDetails accountDetails = new AccountDetails.Builder()
                .account(accountOptional.get())
                .role(role.get())
                .build();

        return ResponseEntity.ok(buildResponseAccountDetailsDto(accountDetails, optionalUser.get()));
    }

    @PutMapping(value = "/user_details/password")
    public void updatePassword(
            @RequestParam(value = "employee_id") Long employee_id,
            @RequestParam(value = "role_id") Long roleId,
            @Valid @RequestBody NewPasswordDto newPassword) {

        Role role = roleRepository
                .findById(roleId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The provided role does not exist."));

        Account account = accountRepository
                .findById(new AccountId(employee_id, role.getId()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no account for the provided employee_id and role."));

        account.setHash(newPassword.getPassword());
        accountRepository.save(account);
    }

    private AccountDetailsDto buildResponseAccountDetailsDto(AccountDetails accountDetails, User user) {
        return new AccountDetailsDto.Builder(accountDetails)
                .licenseCode(user.getLicenseCode())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .streetAddress1(user.getPersonDetails().getStreetAddress1())
                .streetAddress2(user.getPersonDetails().getStreetAddress2())
                .city(user.getPersonDetails().getCity())
                .region(user.getPersonDetails().getRegion())
                .zipCode(user.getPersonDetails().getZipCode())
                .contactNumber(user.getPersonDetails().getContactNumber())
                .build();
    }
}
