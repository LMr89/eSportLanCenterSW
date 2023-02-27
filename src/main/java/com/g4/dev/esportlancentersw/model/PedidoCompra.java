package com.g4.dev.esportlancentersw.model;

import com.g4.dev.esportlancentersw.util.ValidationMessageConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Calendar;

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
    @NotNull(message = ValidationMessageConstants.PRODUCTO_COMPRA)
    private Producto idProducto;

    @Column(nullable = false)
    @NotNull(message = ValidationMessageConstants.CANTIDAD_COMPRA)
    private int cantidad;

    @Column(nullable = false)
    @NotNull(message = ValidationMessageConstants.PRECIO_COMPRA)
    private Double precio;


    @NotNull(message = ValidationMessageConstants.CATEGORIA_COMPRA)
    @ManyToOne
    @JoinColumn(name = "idCategoria", nullable = false)
    private Categoria idCategoria;

    @Column(nullable = false)
    @NotNull(message = ValidationMessageConstants.COMPRABANTE_COMPRA)
    @Size(min = 1, max = 1, message = ValidationMessageConstants.INDICADOR_COMPRABANTE_COMPRA)
    private String tipoComprabante;


    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Calendar fechaRegistro;

    @Column(nullable = false)
    private Boolean estado;

}
