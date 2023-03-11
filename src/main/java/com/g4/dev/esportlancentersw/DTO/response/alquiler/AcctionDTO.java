package com.g4.dev.esportlancentersw.DTO.response.alquiler;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AcctionDTO {
    private String dateAction;
    private  int tipeAction;
    private int  rentedTime;

}
