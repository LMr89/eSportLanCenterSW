package com.g4.dev.esportlancentersw.controller.adviceController;


import com.g4.dev.esportlancentersw.DTO.response.ErrorResponseDTO;
import com.g4.dev.esportlancentersw.exception.pedidoCompra.PedidoCompraInvalidComprabanteException;
import com.g4.dev.esportlancentersw.exception.pedidoCompra.PedidoCanceledException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;

@RestControllerAdvice
public class PedidoCompraAdviceController {

    @ExceptionHandler(PedidoCompraInvalidComprabanteException.class)
    public ResponseEntity<ErrorResponseDTO> invalidComprobanteHandler(PedidoCompraInvalidComprabanteException ex){
        ErrorResponseDTO error = ErrorResponseDTO.builQuickResponse(
                HttpStatus.BAD_REQUEST, Collections.singletonList(ex.getMessage())
        );
        return  new ResponseEntity<>(error, HttpStatus.valueOf(error.getHttpStatus()));
    }

    @ExceptionHandler(PedidoCanceledException.class)
    public ResponseEntity<ErrorResponseDTO> cantCanceledOrderSaleHandler(PedidoCanceledException ex){
        ErrorResponseDTO error = ErrorResponseDTO.builQuickResponse(
                HttpStatus.BAD_REQUEST, Collections.singletonList(ex.getMessage())
        );
        return  new ResponseEntity<>(error, HttpStatus.valueOf(error.getHttpStatus()));
    }
}
