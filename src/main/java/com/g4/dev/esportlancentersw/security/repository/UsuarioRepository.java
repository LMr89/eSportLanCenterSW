package com.g4.dev.esportlancentersw.security.repository;

import com.g4.dev.esportlancentersw.security.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByNomUsuario(String usuario);
    boolean existsByNomUsuario(String usuario);
    boolean existsByEmail(String email);

}
