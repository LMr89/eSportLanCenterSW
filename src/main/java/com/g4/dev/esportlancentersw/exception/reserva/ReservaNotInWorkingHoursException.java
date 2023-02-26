package com.g4.dev.esportlancentersw.exception.reserva;

public class ReservaNotInWorkingHoursException extends RuntimeException{
    public ReservaNotInWorkingHoursException() {
        super("La fecha de la reserva debe ser entre las 9am - 10pm");
    }
}
