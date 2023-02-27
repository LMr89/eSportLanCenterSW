package com.g4.dev.esportlancentersw.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.g4.dev.esportlancentersw.security.model.Usuario;
import com.g4.dev.esportlancentersw.util.ValidationMessageConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    @NotNull(message = ValidationMessageConstants.CLIENTE_NO_ENCONTRADO_EN_VENTA)
    private Cliente idCliente;


    @ManyToOne
    @JoinColumn(nullable = false)
    @NotNull(message = ValidationMessageConstants.USUARIO_NO_ENCONTRADO_EN_VENTA)
    private Usuario idUsuario;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Calendar fecha;

    @Column(nullable = false)
    //@NotNull(message = ValidationMessageConstants.IGV_NO_EN_VENTA)
    private Double igv;

    @Column(nullable = false)
    //@NotNull(message = ValidationMessageConstants.TOTAL_NO_EN_VENTA)
    private Double total;

    @Column(nullable = false)
    private Boolean estado;

    @OneToMany(mappedBy = "venta",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE},
            fetch = FetchType.LAZY)
    private List<DetalleVenta> detalleVentas;

}
