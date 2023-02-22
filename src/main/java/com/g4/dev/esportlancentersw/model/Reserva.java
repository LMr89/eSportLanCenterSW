package com.g4.dev.esportlancentersw.model;

import com.g4.dev.esportlancentersw.security.model.Usuario;
import com.g4.dev.esportlancentersw.util.ValidationMessageConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Calendar;

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


    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Calendar fecha;

    @Column(nullable = false)
    @NotBlank(message = ValidationMessageConstants.TIEMPO_RESERVA)
    private int tiempo;

    @NotBlank(message = ValidationMessageConstants.ORDENADOR_RESERVA)
    @ManyToOne
    @JoinColumn(nullable = false, name = "idOrdernador")
    private Ordenador idOrdenador;

    @ManyToOne
    @JoinColumn(nullable = false, name = "idCliente")
    @NotBlank(message = ValidationMessageConstants.CLIENTE_RESERVA)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(nullable = false, name = "idUsuario")
    @NotBlank(message = ValidationMessageConstants.USUARIO_RESERVA)
    private Usuario idUsuario;

    @Column(nullable = false)
    @NotBlank(message = ValidationMessageConstants.MONTO_RESERVA)
    private Double monto;

    @Column(nullable = false)
    private Boolean estado;



}
