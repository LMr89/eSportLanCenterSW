package com.g4.dev.esportlancentersw.model;

import com.g4.dev.esportlancentersw.util.ValidationMessageConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table
@Entity
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProveedor;

    @Column(nullable = false)
    @NotBlank(message = ValidationMessageConstants.NOMBRE_PROVEEDOR_MSG)
    private String nombre;

    @Column(nullable = false)
    @NotBlank(message = ValidationMessageConstants.NOMBRE_CONTACTO_PROVEEDOR_MSG)
    private String nomContacto;

    @Column(nullable = false)
    @NotBlank(message = ValidationMessageConstants.DIRECCION_PROV_MSG)
    private String direccion;

    @Column(nullable = false)
    private String region;

    @Column(nullable = false)
    @NotBlank(message = ValidationMessageConstants.TELEFONO_PROV_MSG)
    @Size(min = 9, max = 9,message = ValidationMessageConstants.TELEFONO)
    private String telefono;

    @Column
    private String paginaWeb;

    @Column(nullable = false)
    private boolean estado;
}
