package com.g4.dev.esportlancentersw.security.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LoginUsuario {
    private String nomUsuario;
    private String email;
    private String password;
}
