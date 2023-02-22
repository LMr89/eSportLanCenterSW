package com.g4.dev.esportlancentersw.model;

import com.g4.dev.esportlancentersw.util.ValidationMessageConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table
public class PedidoCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCompra;

    @ManyToOne
    @JoinColumn(name = "idProducto", nullable = false)
    @NotBlank(message = ValidationMessageConstants.PRODUCTO_COMPRA)
    private Producto idProducto;

    @Column(nullable = false)
    @NotBlank(message = ValidationMessageConstants.CANTIDAD_COMPRA)
    private int cantidad;

    @Column(nullable = false)
    @NotBlank(message = ValidationMessageConstants.PRECIO_COMPRA)
    private Double precio;

    @Column(nullable = false)
    @NotBlank(message = ValidationMessageConstants.STOCK_COMPRA)
    private int stock;


    @NotBlank(message = ValidationMessageConstants.CATEGORIA_COMPRA)
    @ManyToOne
    @JoinColumn(name = "idCategoria", nullable = false)
    private Categoria idCategoria;

    @Column(nullable = false)
    @NotBlank(message = ValidationMessageConstants.COMPRABANTE_COMPRA)
    private Character tipoComprabante;

    @Column(nullable = false)
    private Boolean estado;

}
