package com.g4.dev.esportlancentersw.DTO.response.venta;

import com.g4.dev.esportlancentersw.model.DetalleVenta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VentaResponse {
    private long idVenta;
    private String clienteNombre;
    private String fecha;
    private double igv;
    private double total;
    private List<DetalleVenta > detalles;
    private String ticketUri;


}
