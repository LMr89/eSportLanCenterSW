package com.g4.dev.esportlancentersw.exception.usuario;

public class UsuarioNomUsuarioRepeatedException extends  RuntimeException{
    public UsuarioNomUsuarioRepeatedException() {
        super("El nombre de usuario ya se encuentra registrado");
    }

    public UsuarioNomUsuarioRepeatedException(String message, Throwable cause) {
        super(message, cause);
    }
}
