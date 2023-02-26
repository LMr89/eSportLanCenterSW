package com.g4.dev.esportlancentersw.exception.reserva;

public class ReservaIsNotFreeException extends RuntimeException{
    public ReservaIsNotFreeException() {
        super("El ordenador ya se encuentra reservado para esa fecha");
    }
}
