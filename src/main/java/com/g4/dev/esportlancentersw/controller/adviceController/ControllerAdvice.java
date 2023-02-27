package com.g4.dev.esportlancentersw.controller.adviceController;

import com.g4.dev.esportlancentersw.DTO.response.ErrorResponseDTO;
import com.g4.dev.esportlancentersw.exception.common.*;
import com.g4.dev.esportlancentersw.util.ExceptionsMessageConstants;
import net.sf.jasperreports.engine.JRException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;

@RestControllerAdvice
public class ControllerAdvice {
    private final Logger log = LogManager.getLogger(ControllerAdvice.class);

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handlerErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }


    @ExceptionHandler(DniRepeatedException.class)
    public ResponseEntity<ErrorResponseDTO> repeatedDniHandler(DniRepeatedException dniex) {
        ErrorResponseDTO dto = ErrorResponseDTO.builQuickResponse(HttpStatus.BAD_REQUEST,
                Collections.singletonList(dniex.getMessage()));
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TelephoneRepeatedException.class)
    public ResponseEntity<ErrorResponseDTO> repeatedTelephoneHandler(TelephoneRepeatedException ex) {
        ErrorResponseDTO dto = ErrorResponseDTO.builQuickResponse(HttpStatus.BAD_REQUEST,
                Collections.singletonList(ex.getMessage()));
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CorreoRepeatedException.class)
    public ResponseEntity<ErrorResponseDTO> repeatedCorreoHandler(CorreoRepeatedException ex) {
        ErrorResponseDTO dto = ErrorResponseDTO.builQuickResponse(HttpStatus.BAD_REQUEST,
                Collections.singletonList(ex.getMessage()));
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> notFoundHandler(NotFoundException ex) {
        ErrorResponseDTO dto = ErrorResponseDTO.builQuickResponse(HttpStatus.NOT_FOUND,
                Collections.singletonList(ex.getMessage()));
        return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
    }


    //Base de datos exception Handler
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponseDTO> SQLServerUniqueConstraint(ConstraintViolationException ex) {
        ErrorResponseDTO dto = ErrorResponseDTO.builQuickResponse(HttpStatus.NOT_FOUND,
                Collections.singletonList(ex.getMessage()));
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(JRException.class)
    public ResponseEntity<ErrorResponseDTO> allExceptionsHandler(JRException ex) {
        ErrorResponseDTO dto = ErrorResponseDTO.builQuickResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                Collections.singletonList(ExceptionsMessageConstants.INTERNAL_SERVER_ERROR_MSG));

        log.error(ex.getMessage());
        return new ResponseEntity<>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotImpletedException.class)
    public ResponseEntity<ErrorResponseDTO> NotImpletedExceptionHandler(NotImpletedException ex) {
        ErrorResponseDTO dto = ErrorResponseDTO.builQuickResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                Collections.singletonList(ExceptionsMessageConstants.INTERNAL_SERVER_ERROR_MSG));

        log.error(ex.getMessage());
        return new ResponseEntity<>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
