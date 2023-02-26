package com.g4.dev.esportlancentersw.exception.producto;

public class ProductoNameRepeatedException extends  RuntimeException{
    public ProductoNameRepeatedException() {
    }

    public ProductoNameRepeatedException(String message) {
        super(message);
    }

    public ProductoNameRepeatedException(String message, Throwable cause) {
        super(message, cause);
    }
}
