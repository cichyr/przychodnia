package pl.clinic.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import pl.clinic.account.controller.dto.AccountBasicsDto;
import pl.clinic.account.controller.dto.AccountDetailsDto;
import pl.clinic.account.model.*;
import pl.clinic.common_services.FilteringService;
import pl.clinic.common_services.UserService;
import pl.clinic.user.model.User;
import pl.clinic.visit.controller.dto.Interview;
import pl.clinic.visit.controller.dto.VisitDetails;
import pl.clinic.visit.model.Visit;

import java.security.Principal;
import java.util.*;

@RestController
public class AccountController {

    @Autowired
    private UserService userService;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    RoleRepository roleRepository;

    @GetMapping(value = "/userinfo", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AccountDetailsDto> getUserInfo(Principal principal) {

        AccountDetails accountDetails;
        Optional<AccountDetails> optionalAccountDetails = resolveAccountDetails(principal);

        if (optionalAccountDetails.isPresent())
            accountDetails = optionalAccountDetails.get();
        else
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "User not authenticated.");

        Optional<? extends User> user =
                userService.findByAccountId(new AccountId(accountDetails.getId(), accountDetails.getRole()));

        if (!user.isPresent())
            return ResponseEntity.ok().build();

        User foundUser = user.get();
        AccountDetailsDto accountDetailsDto = new AccountDetailsDto.Builder(accountDetails)
                .licenseCode(foundUser.getLicenseCode())
                .firstName(foundUser.getFirstName())
                .lastName(foundUser.getLastName())
                .streetAddress1(foundUser.getPersonDetails().getStreetAddress1())
                .streetAddress2(foundUser.getPersonDetails().getStreetAddress2())
                .city(foundUser.getPersonDetails().getCity())
                .region(foundUser.getPersonDetails().getRegion())
                .zipCode(foundUser.getPersonDetails().getZipCode())
                .contactNumber(foundUser.getPersonDetails().getContactNumber())
                .build();

        return ResponseEntity.ok(accountDetailsDto);

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

        List<AccountBasicsDto> accountBasicsDtos = new LinkedList<>();

        for (Account account : accountRepository.findAll()) {
            accountBasicsDtos.add(new AccountBasicsDto(account));
        }

        Optional<? extends User> user;
        for (AccountBasicsDto account : accountBasicsDtos) {
            user = userService.findByAccountId(new AccountId(account.getId(), account.getRole()));

            if (user.isPresent()){
                account.setFirstName(user.get().getFirstName());
                account.setLastName(user.get().getLastName());
            }
        }

        accountBasicsDtos = new FilteringService<>(accountBasicsDtos)
                .contains(userId, AccountBasicsDto::getId)
                .contains(firstName, AccountBasicsDto::getFirstName)
                .contains(lastName, AccountBasicsDto::getLastName)
                .contains(userName, AccountBasicsDto::getUsername)
                .getFiltered();

        return ResponseEntity.ok(accountBasicsDtos);
    }

    @GetMapping(value = "/user_details", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountDetailsDto> getAccountDetails(
            @RequestParam(value = "employee_id") Long accountId,
            @RequestParam(value = "role_id") Long roleId) {

        Optional<Role> role = roleRepository.findById(roleId);

        if (!role.isPresent()) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Role not exist.");
        }

        Optional<? extends User> optionalUser = userService.findByAccountId(new AccountId(accountId, role.get()));

        if (!optionalUser.isPresent()) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Account not exist.");
        }

        Optional<Account> account = accountRepository.findById(new AccountId(accountId, role.get()));

        if (!account.isPresent())
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "User not exist.");

        AccountDetails accountDetails = new AccountDetails(account.get());
        User user = optionalUser.get();

        AccountDetailsDto accountDetailsDto = new AccountDetailsDto.Builder(accountDetails)
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

        return ResponseEntity.ok(accountDetailsDto);
    }

//    //dodaj wywiad
//    @PutMapping(value = "/{visit_id}/interview", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<VisitDetails> putInterview(
//            @PathVariable Long visit_id,
//            @RequestBody Interview interview
//    ) {
//        Optional<Visit> optionalVisit = visitRepository.findById(visit_id);
//        if (!optionalVisit.isPresent()) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//        optionalVisit.get().setDescription(interview.getDescription());
//        optionalVisit.get().setDiagnose(interview.getDiagnose());
//        visitRepository.save(optionalVisit.get());
//
//        return ResponseEntity.ok(new VisitDetails(optionalVisit.get()));
//    }
}
