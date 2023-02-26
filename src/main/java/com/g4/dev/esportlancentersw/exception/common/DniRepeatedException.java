package com.g4.dev.esportlancentersw.exception.common;

public class DniRepeatedException extends RuntimeException{

    public DniRepeatedException() {
        super("El Dni ya esta registrado");
    }

    public DniRepeatedException(String message, Throwable cause) {
        super(message, cause);
    }
}
