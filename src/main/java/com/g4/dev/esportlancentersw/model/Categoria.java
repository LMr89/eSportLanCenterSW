package com.g4.dev.esportlancentersw.model;


import com.g4.dev.esportlancentersw.util.ValidationMessageConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table
@Entity
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategoria;

    @Column(nullable = false)
    @NotNull
    @Size(min = 5, max = 30)
    @NotBlank(message = ValidationMessageConstants.CATEGORIA_NOMBRE_NO_BLANCO)
    private String nombre;

    @Column(nullable = false)
    private Boolean estado;
}
