package com.example.employee.handler;


import com.example.employee.config.MessageTemplate;
import com.example.employee.exeption.EntityValidationException;
import com.example.employee.exeption.PartialUpdateException;
import com.example.employee.exeption.ResourceNotFoundException;
import com.example.employee.model.ErrorDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import java.util.Date;

@ControllerAdvice//Xử lý exception toàn cục cho tất cả Controller
@Slf4j //là annotation của Lombok, tự động tạo logger:
public class GlobalExceptionHandler {

    @Autowired
    private MessageTemplate messageTemplate;

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException e, WebRequest request) {
        log.error(e.toString());
        ErrorDetail errorDetail= new ErrorDetail( new Date() ,e.getMessage(),"",request.getDescription(false));
        return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(EntityValidationException.class)
    public ResponseEntity<?> EntityValidationException(EntityValidationException e, WebRequest request) {
        log.error(e.toString());
        ErrorDetail errorDetail= new ErrorDetail( new Date() ,e.getMessage(),"",request.getDescription(false));
        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(PartialUpdateException.class)
    public ResponseEntity<?> PartialUpdateException(PartialUpdateException e, WebRequest request) {
        log.error(e.toString());
        ErrorDetail errorDetail= new ErrorDetail( new Date() ,messageTemplate.message("error validation"),"",request.getDescription(false));
        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> ResourceNotFoundException(ResourceNotFoundException e, WebRequest request) {
        log.error(e.toString());
        ErrorDetail errorDetail= new ErrorDetail( new Date() ,messageTemplate.message("error validate"),"",request.getDescription(false));
        return new ResponseEntity<>(errorDetail, HttpStatus.NOT_ACCEPTABLE);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandler(Exception e, WebRequest request) {
        log.error(e.toString());
        ErrorDetail errorDetail= new ErrorDetail( new Date() ,messageTemplate.message("error.system"),"",request.getDescription(false));
        return new ResponseEntity<>(errorDetail, HttpStatus.INTERNAL_SERVER_ERROR);

    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> HttpMessageNotReadableException(HttpMessageNotReadableException e, WebRequest request) {
        log.error(e.toString());
        ErrorDetail errorDetail = new ErrorDetail( new Date() ,messageTemplate.message("error.validation"),"",request.getDescription(false));
        return new ResponseEntity<>(errorDetail, HttpStatus.METHOD_NOT_ALLOWED);
    }
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity <?> httpRequestMethodNotSupportedException (HttpRequestMethodNotSupportedException e, WebRequest request) {
        log.error(e.toString());
        ErrorDetail errorDetail = new ErrorDetail( new Date() ,messageTemplate.message("error.system"),"",request.getDescription(false));
        return new ResponseEntity<>(errorDetail, HttpStatus.METHOD_NOT_ALLOWED);
    }
}
