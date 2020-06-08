package pl.clinic.error.model;

import java.util.List;

public class RestError {

    private final List<RestFieldError> errors;

    public RestError(List<RestFieldError> errors) {
        this.errors = errors;
    }

    public List<RestFieldError> getErrors() {
        return errors;
    }
}
