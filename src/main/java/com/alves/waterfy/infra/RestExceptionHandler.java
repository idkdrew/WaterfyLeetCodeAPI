package com.alves.waterfy.infra;

import com.alves.waterfy.exception.CharacterLimitException;
import com.alves.waterfy.exception.InvalidDateFormatException;
import com.alves.waterfy.exception.TaskNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({CharacterLimitException.class})
    private ResponseEntity<String> characterLimitHandler(CharacterLimitException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler({InvalidDateFormatException.class})
    private ResponseEntity<String> invalidDateHandler(InvalidDateFormatException ex){
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(ex.getMessage());
    }

    @ExceptionHandler({TaskNotFoundException.class})
    private ResponseEntity<String> taskNotFoundHandler(TaskNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
