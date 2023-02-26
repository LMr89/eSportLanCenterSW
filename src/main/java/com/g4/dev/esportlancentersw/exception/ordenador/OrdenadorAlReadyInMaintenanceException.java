package com.g4.dev.esportlancentersw.exception.ordenador;

public class OrdenadorAlReadyInMaintenanceException extends RuntimeException{

    public OrdenadorAlReadyInMaintenanceException(String message) {
        super(message);
    }

    public OrdenadorAlReadyInMaintenanceException() {
        super("El ordenador ya se encuentra en mantenimiento");
    }
}
