package com.g4.dev.esportlancentersw.exception.pedidoCompra;

public class PedidoCanceledException extends RuntimeException{
    public PedidoCanceledException() {
        super("No se puede cancelar el pedido debido a insuficiente stock del producto a devolver");
    }
}
