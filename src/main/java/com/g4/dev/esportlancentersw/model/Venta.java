package com.g4.dev.esportlancentersw.model;

import com.g4.dev.esportlancentersw.security.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table
@Entity
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVenta;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Cliente idCliente;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario idUsuario;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Calendar fecha;

    @Column(nullable = false)
    private Double igv;

    @Column(nullable = false)
    private Double total;

    @Column(nullable = false)
    private Boolean estado;

    @OneToMany(mappedBy = "venta",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE},
            fetch = FetchType.LAZY)
    private List<DetalleVenta> detalleVentas;

}
