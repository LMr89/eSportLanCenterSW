package com.g4.dev.esportlancentersw.exception.alquiler;

public class AlquilerNotEqualsForTodayException extends  RuntimeException{
    public AlquilerNotEqualsForTodayException() {
        super("La fecha del alquiler no debe ser menor ni tampoco mayor a la dia actual");
    }

}
