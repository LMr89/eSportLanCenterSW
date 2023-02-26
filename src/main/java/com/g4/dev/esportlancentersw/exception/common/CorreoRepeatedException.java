package com.g4.dev.esportlancentersw.exception.common;

public class CorreoRepeatedException extends  RuntimeException{

    public CorreoRepeatedException() {
        super("El Correo ya se encuentra Registrado");
    }

    public CorreoRepeatedException(String message, Throwable cause) {
        super(message, cause);
    }
}
