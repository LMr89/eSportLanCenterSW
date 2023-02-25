package com.g4.dev.esportlancentersw.controller.adviceController;

import com.g4.dev.esportlancentersw.DTO.response.ErrorResponseDTO;
import com.g4.dev.esportlancentersw.exception.producto.ProductoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

@RestControllerAdvice
public class ProductoAdviceController {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductoNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> prodNotFound(ProductoNotFoundException ex){
        ErrorResponseDTO error = ErrorResponseDTO.builder()
                .httpStatus(HttpStatus.NOT_FOUND.value())
                .tiempo(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()))
                .errores(Arrays.asList(ex.getMessage()))
                .build();
        return  new ResponseEntity<>(error, HttpStatus.NOT_FOUND);


    }
}
