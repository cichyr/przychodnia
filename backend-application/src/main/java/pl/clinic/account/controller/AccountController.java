package pl.clinic.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import pl.clinic.account.controller.dto.AccountDetailsDto;
import pl.clinic.account.model.AccountDetails;
import pl.clinic.account.model.AccountId;
import pl.clinic.common_services.UserService;
import pl.clinic.user.model.User;

import java.security.Principal;
import java.util.Optional;

@RestController
public class AccountController {

    @Autowired
    private UserService userService;

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
                .firstName(foundUser.getFirstName())
                .lastName(foundUser.getLastName())
                .streetAddress1(foundUser.getPersonDetails().getStreetAddress1())
                .streetAddress2(foundUser.getPersonDetails().getStreetAddress2())
                .city(foundUser.getPersonDetails().getCity())
                .region(foundUser.getPersonDetails().getRegion())
                .zipCode(foundUser.getPersonDetails().getZipCode())
                .contactNumber(foundUser.getPersonDetails().getZipCode())
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


}
