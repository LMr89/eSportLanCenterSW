package com.g4.dev.esportlancentersw.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.g4.dev.esportlancentersw.util.ValidationMessageConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table
@Entity
public class DetalleVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalleVenta;

    @ManyToOne
    @JoinColumn(name = "id_venta", nullable = false)
    @JsonIgnore
    private Venta venta;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    @NotNull(message = ValidationMessageConstants.PRODUCTO_NO_EN_VENTA)
    private Producto idProducto;

    @Column
    @NotNull(message = ValidationMessageConstants.CANTIDAD_NO_EN_VENTA)
    private int cantidad;


    @Column(precision = 5, scale = 2)
    @NotNull(message = ValidationMessageConstants.SUBTOTAL_NO_EN_VENTA)
    private Double importe;

    @Column(precision = 5, scale = 2)
    private Double descuento;


}
