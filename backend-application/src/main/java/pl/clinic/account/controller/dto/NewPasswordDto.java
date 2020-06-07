package pl.clinic.account.controller.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NewPasswordDto {
    private String password;

    public String getPassword() {
        return password;
    }

    @JsonCreator
    public NewPasswordDto(@JsonProperty("password") String password) {
        this.password = password;
    }



}
