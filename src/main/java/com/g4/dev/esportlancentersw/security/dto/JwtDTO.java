package com.g4.dev.esportlancentersw.security.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class JwtDTO {

    private String token;
    private String bearer = "BEARER";
    private String nomUsuario;
    private Collection<? extends GrantedAuthority> authorities;
}
