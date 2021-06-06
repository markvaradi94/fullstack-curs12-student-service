package ro.fasttrackit.curs12.student.service.exceptions;

import lombok.Value;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice

public class StudentControllerAdvice {
    @ExceptionHandler(StudentNotFoundException.class)
    @ResponseStatus(BAD_REQUEST)
    ApiError handleStudentNotFoundException(StudentNotFoundException exception) {
        return new ApiError(exception.getMessage());
    }
}

@Value
class ApiError {
    String message;
}
