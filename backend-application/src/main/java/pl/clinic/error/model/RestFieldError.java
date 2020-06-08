package pl.clinic.error.model;

public class RestFieldError {
    private final String field;
    private final String message;

    public RestFieldError(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }
}
