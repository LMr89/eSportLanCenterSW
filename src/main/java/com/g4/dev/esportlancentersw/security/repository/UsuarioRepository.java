package com.g4.dev.esportlancentersw.security.repository;

import com.g4.dev.esportlancentersw.security.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByNomUsuario(String usuario);
    boolean existsByNomUsuario(String usuario);
    boolean existsByCorreo(String email);
    boolean existsByDni(String dni);

    @Query(value = "SELECT u.nombre  FROM Usuario u WHERE u.id  = ?1  ")
    String getNombreById(long id);

}
