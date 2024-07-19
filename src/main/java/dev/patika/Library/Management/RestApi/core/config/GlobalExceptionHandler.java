package dev.patika.Library.Management.RestApi.core.config;


import dev.patika.Library.Management.RestApi.core.exception.NotFoundException;
import dev.patika.Library.Management.RestApi.core.result.Result;
import dev.patika.Library.Management.RestApi.core.utilies.ResultHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Result> handleNotFoundException(NotFoundException e) {
        return new ResponseEntity<>(ResultHelper.notFoundError(e.getMessage()), HttpStatus.NOT_FOUND);
    }
}
