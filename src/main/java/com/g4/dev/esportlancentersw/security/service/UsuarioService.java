package com.g4.dev.esportlancentersw.security.service;


import com.g4.dev.esportlancentersw.security.model.Usuario;
import com.g4.dev.esportlancentersw.security.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    public Optional<Usuario> getByNomUsuario(String nomUsuario){
        return  usuarioRepository.findByNomUsuario(nomUsuario);
    }


    public boolean existsByNomUsuario(String nomUsuario){
        return  usuarioRepository.existsByNomUsuario(nomUsuario);
    }


    public boolean existsByEmail(String email){
        return  usuarioRepository.existsByEmail(email);
    }

    public void saveUsuario(Usuario usuario){
        usuarioRepository.save(usuario);
    }





}
