package com.torq.inventory.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception , WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now() , exception.getMessage(), request.getDescription(false),"RESOURCE_NOT_FOUND");
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceAlreadyExists.class)
    public ResponseEntity<ErrorDetails> handleResourceAlreadyExists(ResourceAlreadyExists exception , WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now() , exception.getMessage(), request.getDescription(false),"RESOURCE_ALREADY_EXISTS");
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalException(Exception exception , WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now() , exception.getMessage(), request.getDescription(false),"INTERNAL_SERVER_ERROR");
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request){
        Map<String , String> validationErrorList = new HashMap<>();
        List<ObjectError> errorList = ex.getBindingResult().getAllErrors();
        errorList.forEach((error)->{
            String fieldName = ((FieldError)error).getField();
            String message = error.getDefaultMessage();
            validationErrorList.put(fieldName,message);
        });
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),validationErrorList.toString(),request.getDescription(false),"VALIDATION_ERROR");
        return new ResponseEntity<>(errorDetails , HttpStatus.BAD_REQUEST);

    }

}
