package com.g4.dev.esportlancentersw.repository;

import com.g4.dev.esportlancentersw.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    boolean existsByCorreo(String correo);
    boolean existsByNomUsuario(String nombreUsuario);
    boolean existsByDni(String dni);

    boolean existsByTelefono(String telefono);
}
