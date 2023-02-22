package com.g4.dev.esportlancentersw.model;

import com.g4.dev.esportlancentersw.util.ValidationMessageConstants;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCliente;

    @NotNull
    @Column(nullable = false)
    @NotBlank(message = ValidationMessageConstants.NOMBRE_USUARIO)
    private String nombre;

    @Column(nullable = false)
    @NotBlank(message = ValidationMessageConstants.APELLIDO_USUARIO)
    private  String apellido;

    @Column(nullable = false, unique = true)
    @NotBlank(message = ValidationMessageConstants.DNI_USUARIO)
    private String dni;

    @Column(nullable = false)
    @NotBlank(message = ValidationMessageConstants.DNI_USUARIO)
    private String direccion;

    @Column(nullable = false)
    @Size(min = 9, max = 9, message = ValidationMessageConstants.TELEFONO)
    private String telefono;

    @Column(nullable = false)
    private Boolean esBloqueado;

    @NotNull
    @Column(unique = true , nullable = false)
    @NotBlank(message = ValidationMessageConstants.DNI_USUARIO)
    private String nomUsuario;

    @NotNull
    @Email(message = ValidationMessageConstants.CORREO)
    private String correo;

    @Column( nullable = false)
    private Boolean estado;
}
