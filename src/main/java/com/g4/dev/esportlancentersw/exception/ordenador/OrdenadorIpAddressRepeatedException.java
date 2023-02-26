package com.g4.dev.esportlancentersw.exception.ordenador;

public class OrdenadorIpAddressRepeatedException extends  RuntimeException{
    public OrdenadorIpAddressRepeatedException() {
        super("La ip del ordenador ya se encuentra en uso");
    }
}
