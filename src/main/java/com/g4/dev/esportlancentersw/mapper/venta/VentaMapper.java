package com.g4.dev.esportlancentersw.mapper.venta;

import com.g4.dev.esportlancentersw.DTO.response.venta.VentaResponse;
import com.g4.dev.esportlancentersw.model.Venta;
import com.g4.dev.esportlancentersw.util.UtilDate;

import javax.servlet.http.HttpServletRequest;

public class VentaMapper {
    private static final StringBuilder stringBuilder;

    static {
        stringBuilder = new StringBuilder();
    }

    public static  VentaResponse mapEntidatToDTO(Venta entity, HttpServletRequest req){
        return  VentaResponse.builder()
                .idVenta(entity.getIdVenta())
                .clienteNombre(entity.getIdCliente().getNombre())
                .fecha(UtilDate.formatDateIntoString(entity.getFecha()))
                .igv(entity.getIgv())
                .total(entity.getTotal())
                .detalles(entity.getDetalleVentas())
                .ticketUri(getUriForShowTicket(req, entity.getIdVenta()))
                .build();

    }
    public static String getUriForShowTicket(HttpServletRequest req , long idVenta) {
        stringBuilder.setLength(0);
        stringBuilder
                .append("http://")
                .append(req.getServerName())
                .append(":")
                .append(req.getServerPort())
                .append(req.getContextPath())
                .append("/reportes/ticketVenta?venta=")
                .append(idVenta);
        return stringBuilder.toString();
    }
}
