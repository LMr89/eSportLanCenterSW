package com.g4.dev.esportlancentersw.model;

import com.g4.dev.esportlancentersw.util.ValidationMessageConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
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

    @Column(nullable = false, unique = true)
    @NotNull
    @Size(min = 5, max = 30)
    @NotBlank(message = ValidationMessageConstants.PRODUCTO_NOMBRE_NO_BLANCO)
    private String nombre;


    @NotNull(message = ValidationMessageConstants.PROVEDOR_PRODCUCTO_MSG)
    @ManyToOne
    @JoinColumn(name = "idProveedor", nullable = false)
    private Proveedor idProveedor;


    @NotNull(message = ValidationMessageConstants.CATEGORIA_PRODUCTO_MSG)
    @ManyToOne
    @JoinColumn(name = "idCategoria", nullable = false)
    private Categoria idCategoria;

    @Column(nullable = false)
    @NotNull(message = ValidationMessageConstants.PRECIO_UNITARIO_MSG)
    @Min(1)
    private Double precioUnitario;

    @Column(nullable = false)
    @Min(1)
    @NotNull(message = ValidationMessageConstants.STOCK_PRODUCTO_MSG)
    private int stock;

    private boolean estado;
}
