package com.g4.dev.esportlancentersw.controller.adviceController;

import com.g4.dev.esportlancentersw.DTO.response.ErrorResponseDTO;
import com.g4.dev.esportlancentersw.exception.cliente.ClienteNameExistsException;
import com.g4.dev.esportlancentersw.exception.producto.ProductoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;


@RestControllerAdvice
public class ClienteControllerAdvice {
    @ExceptionHandler(ClienteNameExistsException.class)
    public ResponseEntity<ErrorResponseDTO> clienteNameExisted(ClienteNameExistsException ex){
        ErrorResponseDTO  error = ErrorResponseDTO.builQuickResponse(HttpStatus.BAD_REQUEST,
                Collections.singletonList(ex.getMessage()));
        return  new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);


    }
}
