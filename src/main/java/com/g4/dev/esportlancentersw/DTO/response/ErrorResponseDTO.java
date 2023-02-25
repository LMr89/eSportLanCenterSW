package com.g4.dev.esportlancentersw.DTO.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

/**
 * Clase encargada para errores devueltas al usuario
 * @author Luis DEV
 * @since 1.0
 */
public class ErrorResponseDTO {

    public ErrorResponseDTO(String mensaje) {
        this.mensaje = mensaje;
    }

    private  int httpStatus;
    private String mensaje;
    private Date tiempo;
    private List<String > errores;



}
