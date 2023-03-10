package com.g4.dev.esportlancentersw.model;

import com.g4.dev.esportlancentersw.util.ValidationMessageConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table
public class Ordenador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrdenador;

    @Column(nullable = false)
    @NotBlank(message = ValidationMessageConstants.NOM_ORDENADOR)
    @Size(min = 5, max = 10 ,message = ValidationMessageConstants.NOMBRE_CORTO)
    private String nomDispositivo;

    @Column(nullable = false)
    @NotBlank(message = ValidationMessageConstants.CARACTERISTICAS_ORDENADOR)
    private String caracteristicas;

    @Column(nullable = false, unique = true)
    @NotBlank(message = ValidationMessageConstants.IP_ORDENADOR)
    @Pattern(regexp = "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$"
    ,message = ValidationMessageConstants.IP_ORDENADOR_NO_VALIDO)
    private String ipOrdenador;

    @Column(nullable = false,length = 20)
    @Nationalized
    @NotBlank(message = ValidationMessageConstants.MAC_ORDENADOR)
    @Pattern(regexp =  "^([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})$",
    message =  ValidationMessageConstants.MAC_ORDENADOR_NO_VALIDO)
    private String direccionMac;

    @Column(nullable = false)
    @NotNull(message = ValidationMessageConstants.NUM_ORDENADOR)
    private int numOrdenador;


    @Column(nullable = false)
    private Boolean mantenimiento;

    @Column(nullable = false)
    private Boolean estado;
}
