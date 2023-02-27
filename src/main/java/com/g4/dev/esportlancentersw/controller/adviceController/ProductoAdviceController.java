package com.g4.dev.esportlancentersw.controller.adviceController;

import com.g4.dev.esportlancentersw.DTO.response.ErrorResponseDTO;
import com.g4.dev.esportlancentersw.exception.producto.ProductoNameRepeatedException;
import com.g4.dev.esportlancentersw.exception.producto.ProductoNoEnoughStockException;
import com.g4.dev.esportlancentersw.exception.producto.ProductoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

@RestControllerAdvice
public class ProductoAdviceController {

    @ExceptionHandler(ProductoNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> prodNotFound(ProductoNotFoundException ex){
        ErrorResponseDTO  error = ErrorResponseDTO.builQuickResponse(HttpStatus.NOT_FOUND,
                Collections.singletonList(ex.getMessage()));
        return  new ResponseEntity<>(error, HttpStatus.NOT_FOUND);


    }

    @ExceptionHandler(ProductoNameRepeatedException.class)
    public ResponseEntity<ErrorResponseDTO> prodNombreRepetido(ProductoNameRepeatedException ex){
        ErrorResponseDTO error = ErrorResponseDTO.builQuickResponse(HttpStatus.BAD_REQUEST,
                Collections.singletonList(ex.getMessage()));
        return  new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);


    }

    @ExceptionHandler(ProductoNoEnoughStockException.class)
    public ResponseEntity<ErrorResponseDTO> ProductoNoEnoughStockExceptionHandler(ProductoNoEnoughStockException ex){
        ErrorResponseDTO error = ErrorResponseDTO.builQuickResponse(HttpStatus.BAD_REQUEST,
                Collections.singletonList(ex.getMessage()));
        return  new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);


    }
}
