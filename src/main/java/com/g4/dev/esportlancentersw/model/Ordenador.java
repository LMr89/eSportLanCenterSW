package com.g4.dev.esportlancentersw.model;

import com.g4.dev.esportlancentersw.util.ValidationMessageConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
    private String ipOrdenador;

    @Column(nullable = false)
    @NotBlank(message = ValidationMessageConstants.NUM_ORDENADOR)
    private int numOrdenador;

    @Column(nullable = false)
    private Boolean estado;
}
