package com.g4.dev.esportlancentersw.security.jwt;

/*
 Se ejecuta por cada peticion para probar los token y permitir
 o negar la peticion
 */


import com.g4.dev.esportlancentersw.security.service.UserDetailServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtTokenFilter  extends OncePerRequestFilter {
    private final static Logger log = LoggerFactory.getLogger(JwtTokenFilter.class);

    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    UserDetailServiceImpl userDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {


        try {
            String token = getToken(request);
            if (token != null && jwtProvider.validateToken(token)){
                String nomUsuario = jwtProvider.getNomUsuarioFromToken(token);
                UserDetails userDetails = userDetailService.loadUserByUsername(nomUsuario);
                UsernamePasswordAuthenticationToken auth =
                            new UsernamePasswordAuthenticationToken(userDetails,
                                    null,
                                    userDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }catch (Exception e){
            log.error("Fail en el método doFilter -> "+e.getMessage());
        }

        filterChain.doFilter(request, response);
    }

    /*
    Método para extraer el token
     */
    private String getToken(HttpServletRequest req){
        String header = req.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer")){
            return header.replace("Bearer", "");
        }
        return  null;
    }

}
