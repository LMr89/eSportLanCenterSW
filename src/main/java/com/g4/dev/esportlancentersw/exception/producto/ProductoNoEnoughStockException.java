package com.g4.dev.esportlancentersw.exception.producto;

public class ProductoNoEnoughStockException extends  RuntimeException{


    public ProductoNoEnoughStockException() {
        super("El producto no tiene suficiente stock para vender");
    }

    public ProductoNoEnoughStockException(String message, Throwable cause) {
        super(message, cause);
    }
}
