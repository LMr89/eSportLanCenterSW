package com.g4.dev.esportlancentersw.security.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

/*
Clase que se utiliza para mandar nuevos usuarios  al cliente
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class NuevoUsuario {

    private String nombre;
    private String nomUsuario;
    private String email;
    private String password;
    private Set<String> roles = new HashSet<>();

}
