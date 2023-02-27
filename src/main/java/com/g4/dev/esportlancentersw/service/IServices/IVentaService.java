package com.g4.dev.esportlancentersw.service.IServices;

import com.g4.dev.esportlancentersw.model.Venta;
import com.g4.dev.esportlancentersw.service.IMainService;

import javax.servlet.http.HttpServletRequest;

public interface IVentaService extends IMainService<Venta> {

    String getUriForShowTicket(HttpServletRequest req, int idVenta);
}
