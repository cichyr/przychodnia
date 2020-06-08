package pl.clinic.account.controller.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import pl.clinic.error.model.ValidationMessages;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class NewPasswordDto {

    @NotBlank(message = ValidationMessages.NOT_BLANK)
    private final  String password;

    public String getPassword() {
        return password;
    }

    @JsonCreator
    public NewPasswordDto(@JsonProperty("password") String password) {
        this.password = password;
    }



}
