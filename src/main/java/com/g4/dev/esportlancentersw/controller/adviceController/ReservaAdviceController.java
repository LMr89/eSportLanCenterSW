package com.g4.dev.esportlancentersw.controller.adviceController;

import com.g4.dev.esportlancentersw.DTO.response.ErrorResponseDTO;
import com.g4.dev.esportlancentersw.exception.cliente.ClienteNameExistsException;
import com.g4.dev.esportlancentersw.exception.reserva.ReservaNotContentForTodayException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;

@RestControllerAdvice
public class ReservaAdviceController {

    @ExceptionHandler(ReservaNotContentForTodayException.class)
    public ResponseEntity<ErrorResponseDTO> clienteNameExisted(ReservaNotContentForTodayException ex){
        ErrorResponseDTO  error = ErrorResponseDTO.builQuickResponse(HttpStatus.NOT_FOUND,
                Collections.singletonList(ex.getMessage()));
        return  new ResponseEntity<>(error, HttpStatus.valueOf(error.getHttpStatus()));


    }
}
