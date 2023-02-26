package com.g4.dev.esportlancentersw.exception.ordenador;

public class OrdenadorNumRepeatedException  extends RuntimeException{
    public OrdenadorNumRepeatedException() {
        super("El numero  del ordenador ya se encuentra en uso");
    }
}
