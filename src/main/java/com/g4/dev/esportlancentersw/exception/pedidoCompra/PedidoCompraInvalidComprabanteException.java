package com.g4.dev.esportlancentersw.exception.pedidoCompra;

import com.g4.dev.esportlancentersw.util.ValidationMessageConstants;

public class PedidoCompraInvalidComprabanteException extends  RuntimeException{
    public PedidoCompraInvalidComprabanteException() {
        super(ValidationMessageConstants.INDICADOR_COMPRABANTE_COMPRA);
    }
}
