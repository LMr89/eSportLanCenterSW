package com.g4.dev.esportlancentersw.exception.ordenador;

public class OrdenadorNameRepearedException extends RuntimeException{
    public OrdenadorNameRepearedException() {
        super("El nombre del ordenador ya se encuentra en uso");
    }
}
