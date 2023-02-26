package com.g4.dev.esportlancentersw.exception.cliente;

public class ClienteNameExistsException extends  RuntimeException{


    public ClienteNameExistsException() {
        super("Nombre de usuario ya existente");
    }

    public ClienteNameExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
