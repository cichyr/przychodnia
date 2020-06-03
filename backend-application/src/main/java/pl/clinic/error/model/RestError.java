package pl.clinic.error.model;

import java.util.List;

public class RestError {

    private final List<String> messages;

    public RestError(List<String> messages) {
        this.messages = messages;
    }

    public List<String> getMessages() {
        return messages;
    }
}
