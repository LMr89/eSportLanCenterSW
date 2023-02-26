package com.g4.dev.esportlancentersw.service.IServices;

import com.g4.dev.esportlancentersw.model.Producto;
import com.g4.dev.esportlancentersw.service.IMainService;

public interface IProductoService extends IMainService<Producto> {
    boolean decrementStockForEverySale(long id, int stockSaled);
    boolean incrementStockByOrderSale(long id, int newStock);
}
