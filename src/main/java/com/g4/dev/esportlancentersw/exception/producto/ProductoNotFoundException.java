package com.g4.dev.esportlancentersw.exception.producto;

public class ProductoNotFoundException extends  RuntimeException{

    public ProductoNotFoundException() {
    }

    public ProductoNotFoundException(String message) {
        super(message);
    }

    public ProductoNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
