package pl.clinic.app_user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import pl.clinic.app_user.controller.dto.AccountDetailsDto;
import pl.clinic.app_user.model.AccountDetails;

import java.security.Principal;

@RestController
public class AccountController {


    ResponseEntity<AccountDetailsDto> getUserInfo(Principal principal) {

        if (!(principal instanceof AccountDetails))
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "Invalid user authentication");

        AccountDetails user = (AccountDetails) principal;

        AccountDetailsDto accountDetailsDto = new AccountDetailsDto.Builder(user).build();

        return ResponseEntity.ok(accountDetailsDto);

    }
}
