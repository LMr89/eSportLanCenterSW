package com.g4.dev.esportlancentersw.DTO.response;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.g4.dev.esportlancentersw.util.UtilDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.text.SimpleDateFormat;
import java.util.Collections;
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

    private int httpStatus;
    @JsonIgnore
    private String mensaje;
    private String tiempo;
    private List<String > errores;

    public static ErrorResponseDTO builQuickResponse(HttpStatus status, List<String> errores){
        return  ErrorResponseDTO.builder()
                .httpStatus(status.value())
                .tiempo(UtilDate.getExactlyDate())
                .errores(errores)
                .build();
    }


}
