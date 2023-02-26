package com.g4.dev.esportlancentersw.exception.proveedor;

public class ProveedorNomContactoRepeatedException extends  RuntimeException {
    public ProveedorNomContactoRepeatedException() {
        super("Proveedor ya existente");
    }
}
