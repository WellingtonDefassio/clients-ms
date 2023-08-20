package bcb.com.br.clients.exception.handle;

import bcb.com.br.clients.exception.ClientNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Objects;

@ControllerAdvice
public class ErrorHandlingControllerAdvice {

    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity<ValidationErrorResponse> onConstraintValidationException(
            ConstraintViolationException e) {
        ValidationErrorResponse error = new ValidationErrorResponse();
        for (ConstraintViolation violation : e.getConstraintViolations()) {
            error.getViolations().add(
                    new Violation(violation.getPropertyPath().toString() + ": " + violation.getMessage()));
        }
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ValidationErrorResponse> onMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {
        ValidationErrorResponse error = new ValidationErrorResponse();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            error.getViolations().add(
                    new Violation(fieldError.getField() + ": " + fieldError.getDefaultMessage()));
        }
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    ResponseEntity<Violation> duplicatedCnpj(DataIntegrityViolationException e) {
        if (Objects.requireNonNull(e.getCause().getCause().getMessage()).contains("duplicate key value violates unique constraint")) {
            return new ResponseEntity<>(new Violation("field cnpj already registered"), HttpStatus.BAD_REQUEST);
        } else {
            return ResponseEntity.internalServerError().build();
        }
    }

    @ExceptionHandler(ClientNotFoundException.class)
    ResponseEntity<Violation> duplicatedCnpj(ClientNotFoundException e) {
        return new ResponseEntity<>(new Violation(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

}
