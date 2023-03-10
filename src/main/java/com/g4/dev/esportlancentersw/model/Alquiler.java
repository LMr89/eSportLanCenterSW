package com.g4.dev.esportlancentersw.model;

import com.g4.dev.esportlancentersw.util.ValidationMessageConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Calendar;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table
@Entity
public class Alquiler {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAlquiler;


    @ManyToOne
    @JoinColumn(name = "idCliente", nullable = false)
    @NotNull(message = ValidationMessageConstants.CLIENTE_NO_ENCONTRADO_EN_ALQUILER)
    private Cliente idCliente;

    @ManyToOne
    @JoinColumn(name = "idOrdenador", nullable = false)
    @NotNull(message = ValidationMessageConstants.ORDENADOR_NO_ENCONTRADO_EN_ALQUILER)
    private Ordenador idOrdenador;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    @NotNull(message = ValidationMessageConstants.FECHA)
    private Calendar fecha;

    @Column(nullable = false)
    @NotNull(message = ValidationMessageConstants.TIEMPO_NO_EN_ALQUILER)
    @Min(value = 30, message = ValidationMessageConstants.MIN_TIEMPO_ALQUILER)
    private int tiempo;

    @Column(nullable = false)
    @NotNull(message = ValidationMessageConstants.MONTO_NO_EN_ALQUILER)
    @Min(value = 2, message = ValidationMessageConstants.MONTO_MINIMO_ALQUILER)
    private Double monto;

    @Column(nullable = false)
    private Boolean estado;
}
