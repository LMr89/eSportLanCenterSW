package com.g4.dev.esportlancentersw.exception;

public class TelephoneRepeatedException extends  RuntimeException{


    public TelephoneRepeatedException() {
        super("El telefono ya se encuentra en uso");
    }

    public TelephoneRepeatedException(String message, Throwable cause) {
        super(message, cause);
    }
}
