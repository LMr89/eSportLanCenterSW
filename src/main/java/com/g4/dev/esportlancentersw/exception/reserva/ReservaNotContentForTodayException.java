package com.g4.dev.esportlancentersw.exception.reserva;

public class ReservaNotContentForTodayException extends  RuntimeException{

    public ReservaNotContentForTodayException() {
        super("No hay reservas registradas para hoy");
    }
}
