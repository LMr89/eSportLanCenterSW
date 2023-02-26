package com.g4.dev.esportlancentersw.model;

import com.g4.dev.esportlancentersw.security.model.Usuario;
import com.g4.dev.esportlancentersw.util.ValidationMessageConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Calendar;

@NamedStoredProcedureQuery(
        name = "listarReservasPorFecha",
        procedureName = "sp_listarReservasPorFechaActual",
        resultClasses = Reserva.class,
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "idOrdenador", type =Long.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "fecharIngresada", type = String.class)
        }
)

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReserva;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    @NotNull(message = ValidationMessageConstants.NO_FECHA_INICIO_ESTABLECIDA)
    private Calendar fechaInicio;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Calendar fechaFin;

    @Column(nullable = false)
    @NotNull(message = ValidationMessageConstants.TIEMPO_RESERVA)
    private int tiempo;

    @NotNull(message = ValidationMessageConstants.ORDENADOR_RESERVA)
    @ManyToOne
    @JoinColumn(nullable = false, name = "idOrdernador")
    private Ordenador idOrdenador;

    @ManyToOne
    @JoinColumn(nullable = false, name = "idCliente")
    @NotNull(message = ValidationMessageConstants.CLIENTE_RESERVA)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(nullable = false, name = "idUsuario")
    @NotNull(message = ValidationMessageConstants.USUARIO_RESERVA)
    private Usuario idUsuario;

    @Column(nullable = false)
    @NotNull(message = ValidationMessageConstants.MONTO_RESERVA)
    private Double monto;

    @Column(nullable = false)
    private Boolean estado;



}
