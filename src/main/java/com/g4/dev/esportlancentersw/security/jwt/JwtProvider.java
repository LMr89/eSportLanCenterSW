package com.g4.dev.esportlancentersw.security.jwt;


import com.g4.dev.esportlancentersw.security.model.UsuarioPrincipal;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

/*
    Clase encargada de generar los tokens
    tambien valida la formacion y la expiracion
 */
@Component
public class JwtProvider {
    private final static Logger log = LoggerFactory.getLogger(JwtProvider.class);

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private int expiration;


    public String generateToken(Authentication authentication){
        UsuarioPrincipal usuarioPrincipal = (UsuarioPrincipal) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(usuarioPrincipal.getNombreUsuario())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date().getTime() + expiration)* 1000))
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();

    }

    public String getNomUsuarioFromToken(String token){
        return Jwts.parser().setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        }catch (MalformedJwtException mjwt){
            log.error("Token mal formado");
        }catch (UnsupportedJwtException mjwt){
            log.error("Token no soportado");
        }catch (ExpiredJwtException mjwt){
            log.error("Token expirado");
        }
        catch (IllegalArgumentException mjwt){
            log.error("Token vacio");
        }
        catch (SignatureException mjwt){
            log.error("Fallo en la firma");
        }
        return  false;
    }
}

