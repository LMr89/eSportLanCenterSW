package com.g4.dev.esportlancentersw.webSocket.exception;

public class NotOrdenadorConnected  extends  RuntimeException{
    public NotOrdenadorConnected() {
        super("El ordenador no esta en linea");
    }
}
