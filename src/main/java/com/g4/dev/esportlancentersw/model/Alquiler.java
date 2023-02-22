package com.g4.dev.esportlancentersw.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    private Cliente idCliente;

    @ManyToOne
    @JoinColumn(name = "idOrdenador", nullable = false)
    private Ordenador idOrdenador;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Calendar fecha;

    @Column(nullable = false)
    private int tiempo;

    @Column(nullable = false)
    private Double Monto;

    @Column(nullable = false)
    private Boolean estado;
}
