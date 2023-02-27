package com.g4.dev.esportlancentersw.service.IServices;

import com.g4.dev.esportlancentersw.model.PedidoCompra;
import com.g4.dev.esportlancentersw.service.IMainService;

public interface IPedidoCompraService extends IMainService<PedidoCompra> {
    boolean existsByIdPedido(long id);
}
