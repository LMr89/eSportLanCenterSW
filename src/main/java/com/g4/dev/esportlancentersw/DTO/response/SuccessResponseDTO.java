package com.g4.dev.esportlancentersw.DTO.response;

import com.g4.dev.esportlancentersw.util.UtilDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.text.SimpleDateFormat;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

/**
 * Clase encargada para acciones exitosas devueltas al usuario
 * @author Luis DEV
 * @since 1.0
 */

public class SuccessResponseDTO {
    private int httpStatus;
    private String mensaje;
    private String tiempo;
    private Object respuesta;


    public static  SuccessResponseDTO buildQuickResponse(String mensaje, Object respuesta){
        return SuccessResponseDTO
                .builder()
                .mensaje(mensaje)
                .httpStatus(HttpStatus.OK.value())
                .tiempo(UtilDate.getExactlyDate())
                .respuesta(respuesta)
                .build();
    }
    public static  SuccessResponseDTO buildQuickResponse(HttpStatus status, String mensaje, Object respuesta){
        return SuccessResponseDTO
                .builder()
                .mensaje(mensaje)
                .httpStatus(status.value())
                .tiempo(UtilDate.getExactlyDate())
                .respuesta(respuesta)
                .build();
    }


}
