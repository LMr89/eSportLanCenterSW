package com.g4.dev.esportlancentersw.security.model;


import com.g4.dev.esportlancentersw.util.ValidationMessageConstants;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(nullable = false)
    @NotBlank(message = ValidationMessageConstants.NOMBRE_USUARIO)
    private String nombre;

    @Column(nullable = false)
    @NotBlank(message = ValidationMessageConstants.APELLIDO_USUARIO)
    private  String apellido;

    @Column(nullable = false, unique = true, length = 8)
    @NotBlank(message = ValidationMessageConstants.DNI_USUARIO)
    private String dni;

    @Column(nullable = false)
    @NotBlank(message = ValidationMessageConstants.DNI_USUARIO)
    private String direccion;

    @Column(nullable = false)
    @Size(min = 9, max = 9, message = ValidationMessageConstants.TELEFONO)
    private String telefono;


    @NotNull
    @Column(unique = true , nullable = false)
    @NotBlank(message = ValidationMessageConstants.DNI_USUARIO)
    private String nomUsuario;



    @NotNull
    @Email(message = ValidationMessageConstants.CORREO)
    private String correo;


    @NotBlank( message = ValidationMessageConstants.PASSWORD_USUARIO)
    @Nationalized
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_rol",joinColumns = @JoinColumn(name = "usuario_id"),
                    inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<Rol> roles = new HashSet<>();
}
