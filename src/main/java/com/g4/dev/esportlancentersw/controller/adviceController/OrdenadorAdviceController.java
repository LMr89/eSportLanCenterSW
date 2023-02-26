package com.g4.dev.esportlancentersw.controller.adviceController;


import com.g4.dev.esportlancentersw.DTO.response.ErrorResponseDTO;
import com.g4.dev.esportlancentersw.exception.cliente.ClienteNameExistsException;
import com.g4.dev.esportlancentersw.exception.ordenador.OrdenadorAlReadyInMaintenanceException;
import com.g4.dev.esportlancentersw.exception.ordenador.OrdenadorIpAddressRepeatedException;
import com.g4.dev.esportlancentersw.exception.ordenador.OrdenadorNameRepearedException;
import com.g4.dev.esportlancentersw.exception.ordenador.OrdenadorNumRepeatedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;

@RestControllerAdvice
public class OrdenadorAdviceController {

    @ExceptionHandler(OrdenadorIpAddressRepeatedException.class)
    public ResponseEntity<ErrorResponseDTO> ordenadorIpHandler(OrdenadorIpAddressRepeatedException ex){
        ErrorResponseDTO  error = ErrorResponseDTO.builQuickResponse(HttpStatus.BAD_REQUEST,
                Collections.singletonList(ex.getMessage()));
        return  new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(OrdenadorNameRepearedException.class)
    public ResponseEntity<ErrorResponseDTO> ordenadorNameHandler(OrdenadorNameRepearedException ex){
        ErrorResponseDTO  error = ErrorResponseDTO.builQuickResponse(HttpStatus.BAD_REQUEST,
                Collections.singletonList(ex.getMessage()));
        return  new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(OrdenadorNumRepeatedException.class)
    public ResponseEntity<ErrorResponseDTO> ordenadorNumHandler(OrdenadorNumRepeatedException ex){
        ErrorResponseDTO  error = ErrorResponseDTO.builQuickResponse(HttpStatus.BAD_REQUEST,
                Collections.singletonList(ex.getMessage()));
        return  new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(OrdenadorAlReadyInMaintenanceException.class)
    public ResponseEntity<ErrorResponseDTO> ordenadorInManintenanceHandler(OrdenadorAlReadyInMaintenanceException ex){
        ErrorResponseDTO  error = ErrorResponseDTO.builQuickResponse(HttpStatus.BAD_REQUEST,
                Collections.singletonList(ex.getMessage()));
        return  new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

    }
}
