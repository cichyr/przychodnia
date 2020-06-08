package pl.clinic.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.clinic.error.model.RestError;
import pl.clinic.error.model.RestFieldError;

import java.util.LinkedList;
import java.util.List;

@ControllerAdvice
public class RestErrorHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RestError> handleValidationException(MethodArgumentNotValidException ex) {

        List<RestFieldError> messages = new LinkedList<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();

            messages.add(new RestFieldError(fieldName, errorMessage));
        });

        return ResponseEntity
                .badRequest()
                .body(new RestError(messages));

    }
}
