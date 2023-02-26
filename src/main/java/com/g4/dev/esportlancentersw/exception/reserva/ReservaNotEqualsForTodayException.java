package com.g4.dev.esportlancentersw.exception.reserva;

public class ReservaNotEqualsForTodayException extends RuntimeException{
    public ReservaNotEqualsForTodayException() {
        super("La fecha de la reserva no debe ser menor a la de hoy ni tampoco mayor");
    }

    public ReservaNotEqualsForTodayException(String message, Throwable cause) {
        super(message, cause);
    }
}
