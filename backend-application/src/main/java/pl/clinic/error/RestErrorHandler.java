package pl.clinic.error;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.clinic.error.model.RestError;

import java.util.LinkedList;
import java.util.List;

@ControllerAdvice
public class RestErrorHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public RestError handleValidationException(MethodArgumentNotValidException ex) {

        List<String> messages = new LinkedList<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            messages.add(fieldName + ": " + errorMessage);
        });

        return new RestError(messages);

    }
}
