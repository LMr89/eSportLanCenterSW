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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table
@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;

    @Column(nullable = false)
    @NotNull
    @Size(min = 5, max = 30)
    @NotBlank(message = ValidationMessageConstants.CATEGORIA_NOMBRE_NO_BLANCO)
    private String nombre;


    @NotBlank(message = ValidationMessageConstants.CATEGORIA_NOMBRE_NO_BLANCO)
    @ManyToOne
    @JoinColumn(name = "idProveedor", nullable = false)
    private Proveedor idProveedor;


    @NotBlank(message = ValidationMessageConstants.CATEGORIA_PRODUCTO_MSG)
    @ManyToOne
    @JoinColumn(name = "idCategoria", nullable = false)
    private Categoria idCategoria;

    @Column(nullable = false)
    @NotNull
    @NotBlank(message = ValidationMessageConstants.PRECIO_UNITARIO_MSG)
    private Double precioUnitario;

    @Column(nullable = false)
    @NotNull
    @Size(min = 5, max = 30)
    @NotBlank(message = ValidationMessageConstants.STOCK_PRODUCTO_MSG)
    private int stock;

    private boolean estado;
}
