package pl.clinic.hello_world.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HelloMessageResource {

    @JsonProperty("hello-message")
    private String message;

    public HelloMessageResource(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
