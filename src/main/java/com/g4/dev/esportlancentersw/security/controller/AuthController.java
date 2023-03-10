package com.g4.dev.esportlancentersw.security.controller;


import com.g4.dev.esportlancentersw.security.dto.JwtDTO;
import com.g4.dev.esportlancentersw.security.dto.LoginUsuario;
import com.g4.dev.esportlancentersw.security.dto.NuevoUsuario;
import com.g4.dev.esportlancentersw.security.enums.RolNombre;
import com.g4.dev.esportlancentersw.security.jwt.JwtProvider;
import com.g4.dev.esportlancentersw.security.model.Rol;
import com.g4.dev.esportlancentersw.security.model.Usuario;
import com.g4.dev.esportlancentersw.security.service.RolService;
import com.g4.dev.esportlancentersw.security.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    PasswordEncoder encoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    RolService rolService;
    @Autowired
    JwtProvider jwtProvider;




    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginUsuario loginUsuario,
                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>("Compos mal puesto", HttpStatus.BAD_REQUEST);
        }
        Authentication authentication =
                authenticationManager.authenticate
                        (new UsernamePasswordAuthenticationToken(loginUsuario.getNomUsuario(), loginUsuario.getPassword()));

            /*
            Se authentica el usuario
             */
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDTO jwtDTO = JwtDTO.builder()
                .token(jwt)
                .bearer("Bearer")
                .nomUsuario(userDetails.getUsername())
                .authorities(userDetails.getAuthorities())
                .build();
        return new ResponseEntity<>(jwtDTO, HttpStatus.OK);
    }


}
